package com.kivi.framework.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Configuration
@ConfigurationProperties( prefix = "spring.task.pool" )
public class TaskThreadPoolConfig {
    private int corePoolSize            = 7;
    private int maxPoolSize             = 42;
    private int queueCapacity           = 11;
    private int keepAliveSeconds        = 5;
    private int awaitTerminationSeconds = 5;
}
