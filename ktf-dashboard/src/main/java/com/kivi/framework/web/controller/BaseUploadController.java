package com.kivi.framework.web.controller;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kivi.framework.cache.CacheKit;
import com.kivi.framework.cache.constant.KtfCache;
import com.kivi.framework.util.kit.CollectionKit;
import com.kivi.framework.util.kit.DateTimeKit;
import com.kivi.framework.util.kit.StrKit;
import com.kivi.framework.web.vo.FileUploadVO;

public class BaseUploadController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(BaseUploadController.class);

    /**
     * 检查分片
     * 
     * @param fvo
     *            文件信息
     * @param savePath
     *            文件保存路径
     * @param userId
     *            登录用户名
     * @return
     */
    protected Object checkChunks( FileUploadVO fvo, String savePath, String userId ) {
        Map<String, Object> result = CollectionKit.newHashMap();

        String newFilePath = userId + "_" + fvo.getName();

        /************************* 检查当前分块是否上传成功 **********************************/
        try {
            // 将当前进度存入缓存
            CacheKit.me().put(KtfCache.UPLOAD_CACHE, FileUploadVO.PRE_PERCENT + newFilePath, fvo.getPercent());

            // 将系统当前时间转换为字符串
            String lastUploadTime = DateTimeKit.now();
            // 将最后上传时间以字符串形式存入redis
            CacheKit.me().put(KtfCache.UPLOAD_CACHE, FileUploadVO.PRE_LAST_UPTIME + newFilePath, lastUploadTime);

            // 自定义文件名： 时间戳（13位）
            String tempFileName = String.valueOf(System.currentTimeMillis());
            String checkFileName = CacheKit.me().get(KtfCache.UPLOAD_CACHE, FileUploadVO.PRE_NAME + fvo.getName());
            if (StrKit.isEmpty(checkFileName)) {
                // 将文件名与该文件上传时生成的存储分片的临时文件夹的名称存入redis
                // 文件上传时生成的存储分片的临时文件夹的名称由MD5和时间戳组成
                checkFileName = StrKit.join("", fvo.getMd5(), tempFileName);
                CacheKit.me().put(KtfCache.UPLOAD_CACHE, FileUploadVO.PRE_NAME + fvo.getName(), checkFileName);
            }

            File dir = new File(StrKit.join("/", savePath, checkFileName));
            if (!dir.exists())
                dir.mkdirs();

            File checkFile = new File(StrKit.join("/", dir.getPath(), fvo.getChunk()));
            // 检查文件是否存在，且大小是否一致
            if (checkFile.exists() && checkFile.length() == fvo.getChunkSize()) {
                // 上传过
                result.put("ifExist", 1);
            }
            else {
                // 没有上传过
                result.put("ifExist", 0);
            }
        }
        catch (Exception e) {
            log.error("检查分片异常", e);
            // 发生异常，则认为没有上传过
            result.put("ifExist", 0);
        }

        return result;
    }

    /**
     * 合并分片
     * 
     * @param fvo
     *            文件信息
     * @param savePath
     *            文件保存路径
     * @param userId
     *            登录用户名
     * @return
     */
    public Map<String, String> mergeChunks( FileUploadVO fvo, String savePath, String userId ) {
        Map<String, String> resultMap = new HashMap<>();

        String newFilePath = userId + "_" + fvo.getName();

        FileOutputStream fout = null;
        // 合并文件
        try {
            // 读取目录里的所有文件
            String cacheFileName = CacheKit.me().get(KtfCache.UPLOAD_CACHE, FileUploadVO.PRE_NAME + fvo.getName());
            File f = new File(savePath + "/" + cacheFileName);
            File[] fileArray = f.listFiles(new FileFilter() {
                // 排除目录只要文件
                @Override
                public boolean accept( File pathname ) {
                    if (pathname.isDirectory()) {
                        return false;
                    }
                    return true;
                }
            });

            // 转成集合，便于排序
            List<File> fileList = Stream.of(fileArray).sorted(Comparator.comparing((File::getName)))
                    .collect(Collectors.toList());

            // 截取文件名的后缀名
            // 最后一个"."的位置
            int pointIndex = fvo.getName().lastIndexOf(".");
            // 后缀名
            String suffix = fvo.getName().substring(pointIndex);

            String saveFile = savePath + "/" + cacheFileName + suffix;
            // 合并后的文件
            File outputFile = new File(saveFile);

            // 输出流
            fout = new FileOutputStream(outputFile);
            FileChannel outChnnel = fout.getChannel();

            // 创建文件
            outputFile.createNewFile();

            // 合并
            FileChannel inChannel;
            for (File file : fileList) {
                inChannel = new FileInputStream(file).getChannel();
                inChannel.transferTo(0, inChannel.size(), outChnnel);
                inChannel.close();
                // 删除分片
                file.delete();
            }

            // 清除文件夹
            File tempFile = new File(savePath + "/" + cacheFileName);
            if (tempFile.isDirectory() && tempFile.exists()) {
                tempFile.delete();
            }

            // 将文件的最后上传时间和生成的文件名返回
            resultMap.put("lastUploadTime",
                    CacheKit.me().get(KtfCache.UPLOAD_CACHE, FileUploadVO.PRE_LAST_UPTIME + newFilePath));
            resultMap.put("pathFileName", saveFile);
            resultMap.put("ext", suffix);

            /**************** 清除redis中的相关信息 **********************/
            // 合并成功后删除redis中的进度信息
            CacheKit.me().remove(KtfCache.UPLOAD_CACHE, FileUploadVO.PRE_PERCENT + newFilePath);
            // 合并成功后删除redis中的最后上传时间，只存没上传完成的
            CacheKit.me().remove(KtfCache.UPLOAD_CACHE, FileUploadVO.PRE_LAST_UPTIME + newFilePath);
            // 合并成功后删除文件名称与该文件上传时生成的存储分片的临时文件夹的名称在redis中的信息 key：上传文件的真实名称
            // value：存储分片的临时文件夹名称（由上传文件的MD5值+时间戳组成）
            // 如果下次再上传同名文件 redis中将存储新的临时文件夹名称 没有上传完成的还要保留在redis中 直到定时任务生效
            CacheKit.me().remove(KtfCache.UPLOAD_CACHE, FileUploadVO.PRE_NAME + fvo.getName());

        }
        catch (Exception e) {
            log.error("合并文件异常", e);
        }
        finally {
            if (fout != null)
                try {
                    fout.close();
                }
                catch (IOException e) {
                    log.error("关闭文件", e);
                }
        }

        return resultMap;
    }

    /**
     * 获取文件上传进度
     * 
     * @param fileName
     * @param userId
     * @return
     */
    protected String getUploadPercent( String fileName, String userId ) {
        String key = StrKit.join("_", userId, fileName);

        String lastPercentage = CacheKit.me().get(KtfCache.UPLOAD_CACHE, FileUploadVO.PRE_PERCENT + key);
        if (StrKit.isBlank(lastPercentage))
            lastPercentage = "0";
        return lastPercentage;
    }
}
