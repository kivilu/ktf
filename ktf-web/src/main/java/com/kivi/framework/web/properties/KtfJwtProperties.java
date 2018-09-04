package com.kivi.framework.web.properties;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.kivi.framework.properties.IKtfProperties;

import lombok.Data;

@ConditionalOnProperty(
                        prefix = KtfJwtProperties.PREFIX,
                        name = "enabled",
                        havingValue = "true",
                        matchIfMissing = false )
@Configuration( KtfJwtProperties.BEAN_NAME )
@ConfigurationProperties( prefix = KtfJwtProperties.PREFIX )
@Data
public class KtfJwtProperties implements IKtfProperties {
    public static final String BEAN_NAME  = "ktfJwtProperties";
    public static final String PREFIX     = "ktf.jwt";

    private Boolean            enabled    = false;
    private String             issuer     = "kivi";
    private String             secretSeed = "kivi.jwt";
    private Long               ttl        = 1800L;

    @Override
    public String prefix() {
        return PREFIX;
    }

    @Override
    public String beanName() {
        return BEAN_NAME;
    }

}
