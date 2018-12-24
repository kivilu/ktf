package com.kivi.framework.web.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.kivi.framework.web.intercepter.CommonIntercepter;
import com.kivi.framework.web.util.xss.XssFilter;

@Configuration
public class KftWebMvcConfigurer extends WebMvcConfigurerAdapter {

    @Autowired
    private CommonIntercepter commonIntercepter;

    @Override
    public void addResourceHandlers( ResourceHandlerRegistry registry ) {

        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

        if (!registry.hasMappingForPattern("/static/**")) {
            registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        }

        super.addResourceHandlers(registry);
    }

    @Override
    public void addViewControllers( ViewControllerRegistry registry ) {
        // TODO Auto-generated method stub
        super.addViewControllers(registry);
    }

    @Override
    public void configureViewResolvers( ViewResolverRegistry registry ) {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view");
        registry.viewResolver(viewResolver);
        super.configureViewResolvers(registry);
    }

    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors( InterceptorRegistry registry ) {
        registry.addInterceptor(commonIntercepter).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    /**
     * xssFilter注册
     */
    @Bean
    public FilterRegistrationBean xssFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new XssFilter());
        registration.addUrlPatterns("/*");
        return registration;
    }

    /**
     * RequestContextListener注册
     */
    @Bean
    public ServletListenerRegistrationBean<RequestContextListener> requestContextListenerRegistration() {
        return new ServletListenerRegistrationBean<>(new RequestContextListener());
    }

    /**
     * ConfigListener注册
     */
    @Bean
    public ServletListenerRegistrationBean<ConfigListener> configListenerRegistration() {
        return new ServletListenerRegistrationBean<>(new ConfigListener());
    }

}
