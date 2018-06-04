package com.kivi.framework.crypto.aes;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * remark:使用AES算法对文件或者字符串进行加密，
 */
public class AESUtil {
    private static final Logger logger = LoggerFactory.getLogger(AESUtil.class);

    /**
     * AES加密
     * 
     * @param key
     *            密钥信息
     * @param content
     *            待加密信息
     */
    public static byte[] encodeAES( byte[] key, byte[] content ) throws Exception {
        // 不是16的倍数的，补足
        int base = 16;
        if (key.length % base != 0) {
            int groups = key.length / base + (key.length % base != 0 ? 1 : 0);
            byte[] temp = new byte[groups * base];
            Arrays.fill(temp, (byte) 0);
            System.arraycopy(key, 0, temp, 0, key.length);
            key = temp;
        }

        SecretKey secretKey = new SecretKeySpec(key, "AES");
        IvParameterSpec iv = new IvParameterSpec(new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 });
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        byte[] tgtBytes = cipher.doFinal(content);
        return tgtBytes;
    }

    /**
     * AES加密
     * 
     * @param key
     *            密钥信息
     * @param content
     *            待加密信息
     */
    public static byte[] encodeAESECB( byte[] key, byte[] content ) throws Exception {
        // 不是16的倍数的，补足
        int base = 16;
        if (key.length % base != 0) {
            int groups = key.length / base + (key.length % base != 0 ? 1 : 0);
            byte[] temp = new byte[groups * base];
            Arrays.fill(temp, (byte) 0);
            System.arraycopy(key, 0, temp, 0, key.length);
            key = temp;
        }

        SecretKey secretKey = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] tgtBytes = cipher.doFinal(content);
        return tgtBytes;
    }

    /**
     * AES解密
     * 
     * @param key
     *            密钥信息
     * @param content
     *            待加密信息
     * @return
     * @throws Exception
     */
    public static byte[] decodeAES( byte[] key, byte[] content ) throws Exception {
        // 不是16的倍数的，补足
        int base = 16;
        if (key.length % base != 0) {
            int groups = key.length / base + (key.length % base != 0 ? 1 : 0);
            byte[] temp = new byte[groups * base];
            Arrays.fill(temp, (byte) 0);
            System.arraycopy(key, 0, temp, 0, key.length);
            key = temp;
        }

        SecretKey secretKey = new SecretKeySpec(key, "AES");
        IvParameterSpec iv = new IvParameterSpec(new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 });
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        byte[] tgtBytes = cipher.doFinal(content);
        return tgtBytes;
    }

    public static byte[] decodeAESECB( byte[] key, byte[] content ) throws Exception {
        // 不是16的倍数的，补足
        int base = 16;
        if (key.length % base != 0) {
            int groups = key.length / base + (key.length % base != 0 ? 1 : 0);
            byte[] temp = new byte[groups * base];
            Arrays.fill(temp, (byte) 0);
            System.arraycopy(key, 0, temp, 0, key.length);
            key = temp;
        }

        SecretKey secretKey = new SecretKeySpec(key, "AES");

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] tgtBytes = cipher.doFinal(content);
        return tgtBytes;
    }

    /**
     * 加密文件
     * 
     * @param key
     *            密钥
     * @param plainFilePath
     *            明文文件路径路径
     * @param secretFilePath
     *            密文文件路径
     * @throws Exception
     */
    public static void encodeAESFile( byte[] key, String plainFilePath,
            String secretFilePath ) throws Exception {
        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;
        FileOutputStream fos = null;
        try {

            fis = new FileInputStream(plainFilePath);
            bos = new ByteArrayOutputStream(fis.available());

            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, count);
            }
            bos.flush();

            byte[] bytes = encodeAES(key, bos.toByteArray());

            fos = new FileOutputStream(secretFilePath);
            fos.write(bytes);
            fos.flush();
        }
        catch (Exception e) {
            throw e;
        }
        finally {
            if (fis != null) {
                try {
                    fis.close();
                }
                catch (Exception e) {
                    logger.error(e.getLocalizedMessage(), e);
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                }
                catch (Exception e) {
                    logger.error(e.getLocalizedMessage(), e);
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                }
                catch (Exception e) {
                    logger.error(e.getLocalizedMessage(), e);
                }
            }
        }
    }

    /**
     * 解密文件
     * 
     * @param key
     *            密钥
     * @param plainFilePath
     *            明文文件路径路径
     * @param secretFilePath
     *            密文文件路径
     * @throws Exception
     */
    public static void decodeAESFile( byte[] key, String plainFilePath,
            String secretFilePath ) throws Exception {
        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(secretFilePath);
            bos = new ByteArrayOutputStream(fis.available());

            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, count);
            }
            bos.flush();

            byte[] bytes = decodeAES(key, bos.toByteArray());

            fos = new FileOutputStream(plainFilePath);
            fos.write(bytes);
            fos.flush();
        }
        catch (Exception e) {
            throw e;
        }
        finally {
            if (fis != null) {
                try {
                    fis.close();
                }
                catch (Exception e) {
                    logger.error(e.getLocalizedMessage(), e);
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                }
                catch (Exception e) {
                    logger.error(e.getLocalizedMessage(), e);
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                }
                catch (Exception e) {
                    logger.error(e.getLocalizedMessage(), e);
                }
            }
        }
    }

    /**
     * 将二进制转换成16进制
     * 
     * @param buf
     * @return
     */
    public static String parseByte2HexStr( byte buf[] ) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     * 
     * @method parseHexStr2Byte
     * @param hexStr
     * @return
     * @throws @since
     *             v1.0
     */
    public static byte[] parseHexStr2Byte( String hexStr ) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
                    16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * 生成符合AES要求的密钥.
     */
    public static byte[] generateDesKey( int length ) {
        // 实例化
        KeyGenerator kgen = null;
        try {
            kgen = KeyGenerator.getInstance("AES");
        }
        catch (NoSuchAlgorithmException e) {
            logger.error("初始化失败", e);
            return null;
        }
        // 设置密钥长度
        kgen.init(length);
        // 生成密钥
        SecretKey skey = kgen.generateKey();
        // 返回密钥的二进制编码
        return skey.getEncoded();
    }

    public static void main( String[] args ) throws Exception {
        // 加密密钥
        /*
         * byte[] key = "1234567887654321".getBytes("GBK"); String
         * content="P|2|200000"+
         * "2015051212106750|6226222900832168|厦门民生测试|100000|很好的银行|我在测试，我好喜欢民生银行..."+
         * "2015051212106751|6226222900832176|民生银行账户|100000|很好的笔记本-----|我在测试，我买了好几个笔记本------"+
         * "######## ";
         * 
         * byte[] encryptResult = encodeAES(key, content.getBytes("GBK"));// 加密
         * byte[] decryptResult = decodeAES(key, encryptResult);// 解密
         * System.out.println("解密后：" + new String(decryptResult));
         * 
         * System.out.println("***********************************************")
         * ; String encryptResultStr = parseByte2HexStr(encryptResult);
         * System.out.println("加密后：" + encryptResultStr); byte[] decryptFrom =
         * parseHexStr2Byte(encryptResultStr); decryptResult = decodeAES(key,
         * decryptFrom);// 解码 System.out.println("解密后：" + new
         * String(decryptResult));
         */

        // AESUtil.decodeAESFile("1FE7C790FBFD0144".getBytes("GBK"),"d:/tmp/res_outer_20150930_001_plain.txt","d:/tmp/res_outer_20150930_001.txt");

        AESUtil.encodeAESFile("1FE7C790FBFD0144".getBytes("GBK"), "d:/tmp/res_outer_20151006_001_plain.txt",
                "d:/tmp/res_outer_20151006_001.txt");
        // AESUtil.decodeAES(key, decoStr.getBytes("GBK"));
    }
}
