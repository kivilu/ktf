package com.kivi.dubbo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;

//@ConditionalOnProperty( prefix = "ktf.dubbo", name = "rest-enabled", havingValue = "true", matchIfMissing = false )
@Configuration
@ImportResource( { "classpath:dubbo/dubbo.xml" } )
public class KtfDubboConfiguration {

    // @Bean
    // @ConfigurationProperties( prefix = "dubbo.application" )
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        return applicationConfig;
    }

    // @Bean
    // @ConfigurationProperties( prefix = "dubbo.registry" )
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        return registryConfig;
    }

    // @Bean
    // @ConfigurationProperties( prefix = "dubbo.protocol" )
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        return protocolConfig;
    }

    // @Bean
    // @ConfigurationProperties( prefix = "dubbo.provider" )
    public ProviderConfig providerConfig() {
        ProviderConfig providerConfig = new ProviderConfig();
        return providerConfig;
    }

    // @Bean
    // @ConfigurationProperties( prefix = "dubbo.consumer" )
    public ConsumerConfig consumerConfig() {
        ConsumerConfig consumerConfig = new ConsumerConfig();
        return consumerConfig;
    }

    // @Bean( "rest" )
    // @ConfigurationProperties( prefix = "dubbo.protocol.rest" )
    public ProtocolConfig protocolRestConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        return protocolConfig;
    }

}
