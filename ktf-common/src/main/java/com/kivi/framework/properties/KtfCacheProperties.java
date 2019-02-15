package com.kivi.framework.properties;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.kivi.framework.component.ApplicationKit;

import lombok.Data;

@Data
@Configuration( KtfCacheProperties.BEAN_NAME )
@ConfigurationProperties( prefix = KtfCacheProperties.PREFIX )
@ConditionalOnProperty(
                        prefix = KtfCacheProperties.PREFIX,
                        name = "enabled",
                        havingValue = "true",
                        matchIfMissing = true )
public class KtfCacheProperties implements IKtfProperties {
    public static final String BEAN_NAME  = "ktfCacheProperties";
    public static final String PREFIX     = "ktf.cache";

    /**
     * KTF缓存配置是否启用，true:启用，false：关闭
     */
    private Boolean            enabled;
    /**
     * 缓存失效时间
     */
    private int                expiration = 3600;
    
    /**
	 * 缓存key的前缀，默认值：jyt-
	 */
	private String prefixKey = "jyt-";


    public String cacheType() {
        return ApplicationKit.me().getEnvProperty("spring.cache.type");
    }

    @Override
    public String prefix() {
        return PREFIX;
    }

    @Override
    public String beanName() {
        return BEAN_NAME;
    }

    @Override
    public String toString() {
        return "KtfCacheProperties [enabled=" + enabled + ", expiration=" + expiration + "]";
    }

}
