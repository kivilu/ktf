package com.kivi.framework.web.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kivi.framework.component.ApplicationKit;

@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener {
    public static final Logger logger = LoggerFactory.getLogger(SessionListener.class);

    @Override
    public void attributeAdded( HttpSessionBindingEvent httpSessionBindingEvent ) {
        logger.info("--attributeAdded--");
        logger.info("key----:" + httpSessionBindingEvent.getName());
        logger.info("value---:" + httpSessionBindingEvent.getValue());

    }

    @Override
    public void attributeRemoved( HttpSessionBindingEvent httpSessionBindingEvent ) {
        logger.info("--attributeRemoved--");
    }

    @Override
    public void attributeReplaced( HttpSessionBindingEvent httpSessionBindingEvent ) {
        logger.info("--attributeReplaced--");
    }

    @Override
    public void sessionCreated( HttpSessionEvent event ) {

        HttpSession session = event.getSession();
        logger.info("---sessionCreated----，sessionId:{}", session.getId());

        ApplicationKit.me().sessionCreated(session.getId());
    }

    @Override
    public void sessionDestroyed( HttpSessionEvent event ) throws ClassCastException {

        HttpSession session = event.getSession();
        logger.info("---sessionDestroyed----，sessionId:{}", session.getId());
        ApplicationKit.me().sessionDestroyed(session.getId());
    }

}
