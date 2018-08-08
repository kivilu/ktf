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

    private Application        application;
    private Registry           registry;
    private Protocol           protocol;

    @Getter
    @Setter
    public static class Application {
        private String name;

        @Override
        public String toString() {
            return "Application [name=" + name + "]";
        }

    }

    @Getter
    @Setter
    public static class Registry {
        private String address;
        private String protocol;

        @Override
        public String toString() {
            return "Registry [address=" + address + ", protocol=" + protocol + "]";
        }

    }

    @Getter
    @Setter
    public static class Protocol {
        private String  name;
        private Integer port;
        private String  threadpool;
        private Integer threads;

        @Override
        public String toString() {
            return "Protocol [name=" + name + ", port=" + port + ", threadpool=" + threadpool + ", threads=" + threads +
                    "]";
        }

    }

    @Override
    public String toString() {
        return "KtfDubboProperties [application=" + application.toString() + ", registry=" + registry.toString() +
                ", protocol=" + protocol.toString() + "]";
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
