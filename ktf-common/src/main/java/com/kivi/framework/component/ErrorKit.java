package com.kivi.framework.component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import com.kivi.framework.constant.Constant;
import com.kivi.framework.constant.GlobalErrorConst;
import com.kivi.framework.properties.KtfProperties;
import com.kivi.framework.util.kit.CollectionKit;
import com.kivi.framework.util.kit.StrKit;

/*
 * 系统错误码通过配置文件+数据库表的方式进行管理。设计原则如下：
 * 1、错误码分为公共错误码及各个业务模块个性错误码，公共错错误码放在error-mapping-global.properties，
 * 各业务模块个性错误码放在各种模块的属性文件中，命名规则需满足error-mapping-*.properties，*的可选值为：PM、AC、BG、CP、NP、OP等
 * 属性文件内容格式：
 * code=alias:desc:[tip] 
 *      code —— 错误码 
 *      alias ——错误码英文别名， 一般和code一起在错误码常量中定义 
 *      desc —— 错误码中文说明 
 *      tip —— 可选内容，错误码详细提示信息，一般用于错误查找错误原因
 * 2、新增错误码：通过在错误码属性文件error-mapping-*.properties中添加实现；
 * 3、修改错误码：上线前，修改属性文件；上线后：通过系统管理接口——错误码更新完成；
 * 
 * 实现过程： 一、启动时 1、从CLASSPATH中加载满足error-mapping-*.properties命名的全部错误码属性文件，放入错误码map；
 * 2、从数据表KTF_ERROR_CODE中读取全部错误码，覆盖错误码map，以保证错误码的描述与数据库同步；
 * 3、同步错误码，将属性文件中新增的错误码更新到数据库。
 * 
 * 二、更新时 1、当需要更新错误码的说明信息时，通过setIsNeedLoad函数修改数据库加载标识状态，从数据库表中加载最新的错误码描述信息。
 * 
 * 注意事项：ErrorKit需要依赖以下三个bean：
 *      springContextHolder、errorCodeService和tk.mybatis.mapper.autoconfigure.MapperAutoConfiguration
 * 
 * @author Eric
 *
 */
@Component( "errorKit" )
public class ErrorKit {

    private static final Logger                    log              = LoggerFactory.getLogger(ErrorKit.class);

    // 错误信息map
    private static ConcurrentMap<String, String[]> errorMap         = new ConcurrentHashMap<>();

    // 错误代码map value的数内容：alias,desc,tip,group
    public static final int                        ERROR_ARRAY_SIZE = 4;

    private ErrorKit() {
    }

    @PostConstruct
    public void loadErrorCode() {
        // 1.从属性文件中加载错误码信息
        Map<String, String[]> errorCodeProp = this.loadFromProperties();
        errorMap.putAll(errorCodeProp);
    }

    public static ErrorKit me() {
        ErrorKit bean = SpringContextHolder.getBeanNoAssert(ErrorKit.class);
        return bean;
    }

    public void updateErrorCodeMap( Map<String, String[]> errorCodes ) {
        errorMap.putAll(errorCodes);
    }

    public Map<String, String[]> getErrorCodeMap() {
        return errorMap;
    }

    public String getDesc( String code ) {
        String desc = code;
        String[] result = errorMap.get(code);
        if (result == null)
            return desc;

        return result[ERROR_ARRAY_SIZE - 3];
    }

    public String success() {
        return this.getDesc(GlobalErrorConst.SUCCESS);
    }

    public String getTip( String code ) {
        String desc = code;
        String[] result = errorMap.get(code);
        if (result == null)
            return desc;

        return result[ERROR_ARRAY_SIZE - 2];
    }

    private Map<String, String[]> loadFromProperties() {
        Map<String, String[]> result = new HashMap<>();
        String errorMappingFilePattern = "classpath*:error-mapping-*.properties";
        KtfProperties ktfProperties = SpringContextHolder.getBean(KtfProperties.class);
        if (ktfProperties != null)
            errorMappingFilePattern = ktfProperties.getCommon().getErrorMappingFilePattern();

        log.info("错误码属性文件加载路径：{}", errorMappingFilePattern);

        Resource[] resources = ApplicationKit.me().getResources(errorMappingFilePattern);
        for (Resource r : resources) {
            try {
                log.info("开始加载错误码属性文件:{}", r.getURL().getPath());
                Properties prop = PropertiesLoaderUtils
                        .loadProperties(new EncodedResource(r, Constant.DEFAULT_CHARSET));

                String group = StrKit.removePrefix(r.getFilename(), "error-mapping-");
                group = StrKit.removeSuffix(group, ".properties").toUpperCase();

                @SuppressWarnings( { "unchecked", "rawtypes" } )
                Map<String, String> map = new HashMap<String, String>((Map) prop);

                Iterator<Entry<String, String>> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Entry<String, String> ent = it.next();
                    String[] items = StrKit.split(ent.getValue(), ":");
                    if (items.length < 2) {
                        log.warn("无效的错误码，{}：{}", ent.getKey(), ent.getValue());
                        continue;
                    }

                    String[] values = (items.length == ERROR_ARRAY_SIZE - 1) ? items
                            : CollectionKit.append(items, items[1]);
                    // 添加 group
                    values = CollectionKit.append(values, group);

                    result.put(ent.getKey(), values);
                }

            }
            catch (IOException e) {
                log.warn("加载错误码属性文件异常", e);
            }
        }

        return result;
    }

}
