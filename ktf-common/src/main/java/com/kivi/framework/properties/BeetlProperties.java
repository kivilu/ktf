package com.kivi.framework.properties;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.kivi.framework.util.kit.StrKit;

import lombok.Getter;
import lombok.Setter;

/**
 * beetl配置(如果需要配置别的配置可参照这个形式自己添加)
 *
 */
@Setter
@Getter
@Configuration
@ConfigurationProperties( prefix = BeetlProperties.BEETLCONF_PREFIX )
@ConditionalOnProperty(
                        prefix = "ktf.beetl",
                        name = "enabled",
                        havingValue = "true",
                        matchIfMissing = false )
public class BeetlProperties {

    public static final String BEETLCONF_PREFIX = "ktf.beetl";

    private String             delimiterStatementStart;

    private String             delimiterStatementEnd;

    private String             resourceTagroot;

    private String             resourceTagsuffix;

    private String             resourceAutoCheck;

    @Value( "${spring.mvc.view.prefix}" )
    private String             prefix;

    public Properties getProperties() {
        Properties properties = new Properties();
        if (StrKit.isNotEmpty(delimiterStatementStart)) {
            if (delimiterStatementStart.startsWith("\\")) {
                delimiterStatementStart = delimiterStatementStart.substring(1);
            }
            properties.setProperty("DELIMITER_STATEMENT_START", delimiterStatementStart);
        }
        if (StrKit.isNotEmpty(delimiterStatementEnd)) {
            properties.setProperty("DELIMITER_STATEMENT_END", delimiterStatementEnd);
        }
        else {
            properties.setProperty("DELIMITER_STATEMENT_END", "null");
        }
        if (StrKit.isNotEmpty(resourceTagroot)) {
            properties.setProperty("RESOURCE.tagRoot", resourceTagroot);
        }
        if (StrKit.isNotEmpty(resourceTagsuffix)) {
            properties.setProperty("RESOURCE.tagSuffix", resourceTagsuffix);
        }
        if (StrKit.isNotEmpty(resourceAutoCheck)) {
            properties.setProperty("RESOURCE.autoCheck", resourceAutoCheck);
        }
        return properties;
    }

}
