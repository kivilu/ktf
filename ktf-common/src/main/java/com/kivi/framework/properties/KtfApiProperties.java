package com.kivi.framework.properties;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Configuration( KtfApiProperties.BEAN_NAME )
@ConfigurationProperties( prefix = KtfApiProperties.PREFIX )
@ConditionalOnProperty(
                        prefix = KtfApiProperties.PREFIX,
                        name = "enabled",
                        havingValue = "true",
                        matchIfMissing = false )
public class KtfApiProperties implements IKtfProperties {
    public static final String BEAN_NAME = "ktfApiProperties";
    public static final String PREFIX    = "ktf.api";

    private Long               timeout   = 30000L;

    @Override
    public String toString() {
        return "Api [timeout=" + timeout + "]";
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
