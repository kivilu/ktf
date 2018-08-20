package com.kivi.dubbo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.ImportResource;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.deepoove.swagger.dubbo.annotations.EnableDubboSwagger;

@Inherited
@Retention( RetentionPolicy.RUNTIME )
@Target( { ElementType.TYPE } )
@EnableDubbo( scanBasePackages = { "com.kivi", "${ktf.dubbo.scan-base-packages}" } )
@EnableDubboSwagger
@ImportResource( { "classpath:dubbo/dubbo-rest.xml" } )
public @interface KtfEnableDubbo {

}
