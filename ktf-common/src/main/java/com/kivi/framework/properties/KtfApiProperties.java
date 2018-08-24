package com.kivi.framework.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration( KtfApiProperties.BEAN_NAME )
@ConfigurationProperties( prefix = KtfApiProperties.PREFIX )
public class KtfApiProperties implements IKtfProperties {
    public static final String BEAN_NAME = "ktfApiProperties";
    public static final String PREFIX    = "ktf.api";

    /**
     * KTF-API配置是否启用，true:启用，false：关闭
     */
    private Boolean            enabled;

    /**
     * 超时时间
     */
    private Long               timeout   = 30000L;

    @Override
    public String prefix() {
        return PREFIX;
    }

    @Override
    public String beanName() {
        return BEAN_NAME;
    }

}
