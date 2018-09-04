package com.kivi.framework.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.kivi.framework.properties.KtfCommonProperties;

@ConditionalOnProperty(
                        prefix = KtfCommonProperties.PREFIX,
                        name = "apollo-enabled",
                        havingValue = "true",
                        matchIfMissing = false )
@Configuration
@EnableApolloConfig
public class KtfApolloConfiguration {

}
