package com.kivi.framework.properties;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Configuration( KtfDubboProperties.BEAN_NAME )
@ConfigurationProperties( prefix = KtfDubboProperties.PREFIX )
@ConditionalOnProperty(
                        prefix = KtfDubboProperties.PREFIX,
                        name = "enabled",
                        havingValue = "true",
                        matchIfMissing = false )
public class KtfDubboProperties implements IKtfProperties {
    public static final String BEAN_NAME = "ktfDubboProperties";
    public static final String PREFIX    = "ktf.dubbo";

    private Boolean            enabled;
    /**
     * Dubbo Service 扫描包，多个包名直接采用“,”分割
     */
    private String             scanBasePackages;
    /**
     * dubbo启用Kryo和FST序列化工具时注册的类，多个类名之间使用“,”分割
     */
    private String             serializeClasses;

    @Override
    public String prefix() {
        return PREFIX;
    }

    @Override
    public String beanName() {
        return BEAN_NAME;
    }

}
