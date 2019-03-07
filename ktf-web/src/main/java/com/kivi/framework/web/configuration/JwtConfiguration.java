package com.kivi.framework.web.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.kivi.framework.util.kit.StrKit;
import com.kivi.framework.web.intercepter.JwtAuthInterceptor;
import com.kivi.framework.web.properties.KtfJwtProperties;

@ConditionalOnProperty(
                        prefix = KtfJwtProperties.PREFIX,
                        name = "enabled",
                        havingValue = "true",
                        matchIfMissing = false )
@Configuration
public class JwtConfiguration extends WebMvcConfigurerAdapter {
    @Autowired( required = false )
    private KtfJwtProperties ktfJwtProperties;

    @Override
    public void addInterceptors( InterceptorRegistry registry ) {
        InterceptorRegistration registration = registry.addInterceptor(jwtAuthInterceptor());

        String[] excludePathPatterns = StrKit.split(ktfJwtProperties.getExcludePathPatterns(), ",");

        // 排除的路径
        for (String excludePathPattern : excludePathPatterns) {
            registration.excludePathPatterns(excludePathPattern);
        }

        // 将这个controller放行
        registration.excludePathPatterns("/error");
        // 拦截全部
        registration.addPathPatterns("/**");

        // super.addInterceptors(registry);
    }

    @Bean
    public JwtAuthInterceptor jwtAuthInterceptor() {
        return new JwtAuthInterceptor();
    }

}
