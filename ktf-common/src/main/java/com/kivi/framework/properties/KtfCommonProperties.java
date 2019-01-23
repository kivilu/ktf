package com.kivi.framework.properties;

import java.io.File;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.kivi.framework.util.kit.StrKit;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Configuration( KtfCommonProperties.BEAN_NAME )
@ConfigurationProperties( prefix = KtfCommonProperties.PREFIX )
public class KtfCommonProperties implements IKtfProperties {
    public static final String BEAN_NAME               = "ktfCommonProperties";
    public static final String PREFIX                  = "ktf.common";

    private String             errorMappingFilePattern = "classpath*:error-mapping-*.properties";
    private String             componentScan           = "com.kivi";
    private String             txPointcutExpression;
    private String             txAdviceRequired        = "insert*,update*,delete*,save*,modify*,add*";
    private String             txAdviceSupports        = "find*,get*,query*,list*,select*";
    private String             txAdviceNotSupported    = "log*";
    private String             sidDir                  = "/app/sid";
    private Boolean            kaptchaOpen             = false;
    private String             fileUploadPath          = "/app/upload";
    private Boolean            enableApollo            = true;

    /**
     * 测试是否开启
     */
    private Boolean            enableTest              = false;

    public String getFileUploadPath() {
        if (!StrKit.endWith(fileUploadPath, "/", true))
            fileUploadPath += "/";

        File dir = new File(fileUploadPath).getAbsoluteFile();
        if (!dir.exists())
            dir.mkdirs();

        return fileUploadPath;
    }

    @Override
    public String toString() {
        return "KtfCommonProperties [errorMappingFilePattern=" + errorMappingFilePattern + ", componentScan=" +
                componentScan + ", txPointcutExpression=" + txPointcutExpression + ", txAdviceRequired=" +
                txAdviceRequired + ", txAdviceSupports=" + txAdviceSupports + ", txAdviceNotSupported=" +
                txAdviceNotSupported + ", sidDir=" + sidDir + ", kaptchaOpen=" + kaptchaOpen + ", fileUploadPath=" +
                fileUploadPath + "]";
    }

    @Override
    public String prefix() {
        return PREFIX;
    }

    @Override
    public String beanName() {
        return BEAN_NAME;
    }

}
