package com.kivi.framework.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.context.scope.refresh.RefreshScope;
import org.springframework.stereotype.Component;

import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.kivi.framework.properties.IKtfProperties;

@ConditionalOnProperty( name = { "ktf.common.apollo-enabled" }, havingValue = "true", matchIfMissing = false )
@EnableApolloConfig
@Component
public class ApolloRefreshConfig {

    private static final Logger logger = LoggerFactory.getLogger(ApolloRefreshConfig.class);

    @Autowired
    private RefreshScope        refreshScope;

    @Autowired
    private IKtfProperties[]    ktfConfigs;

    @ApolloConfigChangeListener
    public void onChange( ConfigChangeEvent changeEvent ) {
        for (String changedKey : changeEvent.changedKeys()) {
            for (IKtfProperties ktfConfig : ktfConfigs) {
                if (changedKey.startsWith(ktfConfig.prefix())) {
                    logger.info("Properties[{}], before refresh {}", ktfConfig.prefix(), ktfConfig.toString());
                    refreshScope.refresh(ktfConfig.beanName());
                    logger.info("Properties[{}], after refresh {}", ktfConfig.prefix(), ktfConfig.toString());
                    break;
                }
            }

        }

    }

}
