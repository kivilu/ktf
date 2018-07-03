package com.kivi.framework.web.configuration;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import com.kivi.framework.web.shiro.freemarker.ShiroTags;

@Configuration
@ConditionalOnProperty(
                        prefix = "spring.freemarker",
                        name = "enabled",
                        havingValue = "true",
                        matchIfMissing = false )
public class FreeMarkerConfig {
    private static final Logger               log = LoggerFactory.getLogger(FreeMarkerConfig.class);

    @Autowired
    private freemarker.template.Configuration configuration;

    @PostConstruct
    public void setSharedVariable() {
        try {
            configuration.setSharedVariable("shiro", new ShiroTags());
        }
        catch (Exception e) {
            log.error("设置共享变量异常", e);
        }
    }
}
