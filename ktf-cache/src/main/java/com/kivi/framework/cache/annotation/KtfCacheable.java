package com.kivi.framework.cache.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

@Target( { ElementType.METHOD, ElementType.TYPE } )
@Retention( RetentionPolicy.RUNTIME )
@Inherited
@Documented
public @interface KtfCacheable {

    @AliasFor( "cacheNames" )
    String[] value() default {};

    @AliasFor( "value" )
    String[] cacheNames() default {};

    String key() default "";
}
