package com.kivi.framework.log.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.kivi.framework.dto.warapper.WarpperReqDTO;
import com.kivi.framework.dto.warapper.WarpperRspDTO;
import com.kivi.framework.log.LogManager;
import com.kivi.framework.log.annotation.BizLog;
import com.kivi.framework.log.factory.LogTaskFactory;
import com.kivi.framework.util.kit.DateTimeKit;
import com.kivi.framework.util.kit.StrKit;

/**
 * 日志记录
 *
 */
@Aspect
@Component
public class BizLogAspect {

    private Logger                     log           = LoggerFactory.getLogger(this.getClass());

    private ThreadLocal<Long>          msThreadLocal = new ThreadLocal<Long>();

    private static ThreadLocal<Object> reqObjects    = new ThreadLocal<Object>();

    private static ThreadLocal<Method> methodLocal   = new ThreadLocal<Method>();

    @Pointcut( value = "@annotation(com.kivi.framework.log.annotation.BizLog)" )
    public void aopMethed() {
    }

    @Before( value = "aopMethed()" )
    public void beforMethedProceed( JoinPoint joinPoint ) throws Throwable {

        // 获取方法对象
        Signature sig = joinPoint.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Object target = joinPoint.getTarget();
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        methodLocal.set(currentMethod);

        // 获取注解
        BizLog annotation = currentMethod.getAnnotation(BizLog.class);

        // 获取参数
        Object[] params = joinPoint.getArgs();
        for (Object param : params) {
            if (param instanceof WarpperReqDTO<?>) {
                log.info("调用{}服务，调用发起方：{}", annotation.value(), ((WarpperReqDTO<?>) param).getFromAppName());
            }
            else if (param instanceof WarpperRspDTO<?>) {
                log.info("调用{}服务，调用发起方：{}", annotation.value(), ((WarpperRspDTO<?>) param).getFromAppName());
            }
        }

    }

    @Around( "aopMethed()" )
    public Object methedProceed( ProceedingJoinPoint point ) throws Throwable {

        msThreadLocal.set(System.currentTimeMillis());

        // 执行业务
        Object result = point.proceed();

        return result;
    }

    @AfterReturning( value = "aopMethed()", returning = "returnValue" )
    public void afterReturningProceed( JoinPoint joinPoint, Object returnValue ) {

        String className = joinPoint.getTarget().getClass().getName();
        String methodName = methodLocal.get().getName();
        Object[] params = joinPoint.getArgs();

        // 获取操作名称
        BizLog annotation = methodLocal.get().getAnnotation(BizLog.class);
        String bussinessName = annotation.value();
        boolean isSave = annotation.isSave();

        log.debug("===={}.{}执行耗时：{}ms", className, methodName,
                DateTimeKit.spendMs(msThreadLocal.get()));

        String msg = "";
        if (log.isTraceEnabled()) {
            msg = StrKit.join(StrKit.AND, params);
            log.trace("===={}.{} 参数：{}", className, methodName, msg);
        }

        if (isSave) {
            // TODO：待修改代码
            String userId = "";

            msg = StrKit.join(StrKit.AND, params);

            LogManager.me().executeLog(LogTaskFactory.bussinessLog(userId, bussinessName, className, methodName, msg));
        }

    }

}
