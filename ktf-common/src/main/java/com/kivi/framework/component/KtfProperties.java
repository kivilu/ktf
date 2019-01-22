package com.kivi.framework.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kivi.framework.properties.KtfApiProperties;
import com.kivi.framework.properties.KtfCacheProperties;
import com.kivi.framework.properties.KtfCommonProperties;
import com.kivi.framework.properties.KtfShiroProperties;
import com.kivi.framework.properties.KtfSwaggerProperties;

import lombok.Data;

@Data
@Component
public class KtfProperties {

    @Autowired( required = false )
    private KtfCommonProperties  common;
    @Autowired( required = false )
    private KtfCacheProperties   cache;
    @Autowired( required = false )
    private KtfSwaggerProperties swagger;
    @Autowired( required = false )
    private KtfShiroProperties   shiro;
    @Autowired( required = false )
    private KtfApiProperties     api;

}
