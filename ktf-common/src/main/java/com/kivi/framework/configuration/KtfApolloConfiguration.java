package com.kivi.framework.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.kivi.framework.properties.KtfCommonProperties;

@Configuration
@ConditionalOnProperty(
                        prefix = KtfCommonProperties.PREFIX,
                        name = "apollo-enabled",
                        havingValue = "true",
                        matchIfMissing = false )
@EnableApolloConfig
public class KtfApolloConfiguration {

}
