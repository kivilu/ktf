package com.kivi.framework.task;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

public class KtfAsyncUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(KtfAsyncUncaughtExceptionHandler.class);

    @Override
    public void handleUncaughtException( Throwable ex, Method method, Object... params ) {
        log.info("-------------》》》捕获线程异常信息");
        log.info("Exception message: {}", ex.getMessage(), ex);
        log.info("Method name: {} ", method.getName());
        for (Object param : params) {
            log.info("Parameter value: {}", param);
        }
    }

}
