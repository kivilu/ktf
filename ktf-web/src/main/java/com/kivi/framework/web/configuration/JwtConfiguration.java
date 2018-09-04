package com.kivi.framework.web.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.kivi.framework.web.intercepter.JwtAuthInterceptor;
import com.kivi.framework.web.properties.KtfJwtProperties;

@ConditionalOnProperty(
                        prefix = KtfJwtProperties.PREFIX,
                        name = "enabled",
                        havingValue = "true",
                        matchIfMissing = false )
@Configuration
public class JwtConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors( InterceptorRegistry registry ) {
        registry.addInterceptor(jwtAuthInterceptor())
                .addPathPatterns("/**"); // 拦截所有请求，通过判断是否有 @LoginToken 注解
                                         // 决定是否需要登录
        super.addInterceptors(registry);
    }

    @Bean
    public JwtAuthInterceptor jwtAuthInterceptor() {
        return new JwtAuthInterceptor();
    }

}
