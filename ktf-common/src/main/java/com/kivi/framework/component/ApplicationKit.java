package com.kivi.framework.component;

import java.io.IOException;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.kivi.framework.exception.ToolBoxException;
import com.kivi.framework.service.ISessionNotify;
import com.kivi.framework.util.IdWalker;

/**
 * 应用帮助类，主要用于获取SpringBoot应用的相关信息
 * 
 * @author Eric
 *
 */
@Component
public class ApplicationKit {

    @Autowired
    private ApplicationContext             applicationContext;

    @Autowired
    private Environment                    env;

    private static HashSet<ISessionNotify> sessionNotifySet = new HashSet<>();

    private static IdWalker                idWalker         = new IdWalker();

    public static ApplicationKit me() {

        return SpringContextHolder.getBean(ApplicationKit.class);
    }

    public int getServerPort() {
        ServerProperties serverProperties = SpringContextHolder.getBean(ServerProperties.class);
        if (serverProperties == null)
            return -1;
        return serverProperties.getPort();
    }

    public String getServerContext() {
        ServerProperties serverProperties = SpringContextHolder.getBean(ServerProperties.class);
        if (serverProperties == null)
            return null;
        return serverProperties.getContextPath();
    }

    public String getAppcationName() {
        String name = env.getProperty("spring.application.name");
        return name;
    }

    public long nextId() {
        return idWalker.nextId();
    }

    public Resource[] getResources( String locationPattern ) {
        Resource[] resources = null;

        try {
            resources = applicationContext.getResources(locationPattern);
        }
        catch (IOException e) {
            throw new ToolBoxException("获取资源文件异常", e);
        }

        return resources;
    }

    public void sessionCreated( String id ) {

    }

    public void sessionDestroyed( String id ) {
        sessionNotifySet.stream().forEach(notify-> notify.onDestroyed(id));
    }

}
