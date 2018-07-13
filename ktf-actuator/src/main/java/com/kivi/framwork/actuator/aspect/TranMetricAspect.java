package com.kivi.framwork.actuator.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.kivi.framework.constant.GlobalErrorConst;
import com.kivi.framework.util.kit.StrKit;
import com.kivi.framwork.actuator.annotation.TranMetric;
import com.kivi.framwork.actuator.annotation.enums.MetircStep;
import com.kivi.framwork.actuator.annotation.enums.MetircType;

/**
 * 日志记录
 *
 */
@Aspect
@Component
public class TranMetricAspect {

    private Logger                  log         = LoggerFactory.getLogger(this.getClass());

    private ThreadLocal<TranMetric> tranMetrics = new ThreadLocal<TranMetric>();

    private final CounterService    counterService;

    @Autowired
    public TranMetricAspect( CounterService counterService ) {
        this.counterService = counterService;
    }

    @Pointcut( value = "@annotation(com.kivi.framwork.actuator.annotation.TranMetric)" )
    public void aopMethed() {
    }

    @Around( "aopMethed()" )
    public Object tranMetric( ProceedingJoinPoint point ) throws Throwable {

        // 获取拦截的方法名
        Signature sig = point.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于.方法");
        }
        msig = (MethodSignature) sig;
        Object target = point.getTarget();

        log.trace("交易统计，方法：{}.{}", target.getClass().getName(), msig.getName());

        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());

        // 获取操作名称
        TranMetric annotation = currentMethod.getAnnotation(TranMetric.class);
        tranMetrics.set(annotation);
        beforeProcess(annotation);
        // 先执行业务
        Object result = point.proceed();
        afterProcess(annotation, result);

        return result;
    }

    @AfterThrowing( value = "aopMethed()", throwing = "ex" )
    public void tranExceptionMetric( Exception ex ) {
        TranMetric annotation = tranMetrics.get();
        throwingProcess(annotation);
    }

    @AfterReturning( value = "aopMethed()", returning = "returnValue" )
    public void afterReturningProceed( JoinPoint joinPoint, Object returnValue ) {

    }

    /**
     * 函数处理前的度量
     * 
     * @param annotation
     */
    private void beforeProcess( TranMetric annotation ) {
        MetircStep step = annotation.step();

        if (MetircStep.OneStep.ordinal() == step.ordinal()) {
            counter(annotation.value(), MetircType.Total);
            counter(annotation.value(), MetircType.Processing, false);
        }
        else if (MetircStep.Before.ordinal() == step.ordinal()) {
            counter(annotation.value(), MetircType.Total);
            counter(annotation.value(), MetircType.Queued);
        }
        else if (MetircStep.Doing.ordinal() == step.ordinal()) {
            counter(annotation.value(), MetircType.Processing);
        }
    }

    private void afterProcess( TranMetric annotation, Object result ) {
        MetircStep step = annotation.step();
        if (MetircStep.OneStep.ordinal() == step.ordinal() || MetircStep.Before.ordinal() == step.ordinal()) {
            String rspJson = null;
            if (result instanceof ResponseEntity<?>) {
                Object obj = ((ResponseEntity<?>) result).getBody();
                rspJson = JSON.toJSONString(obj);
            }
            if (rspJson == null || StrKit.containsIgnoreCase(rspJson, GlobalErrorConst.SUCCESS))
                counter(annotation.value(), MetircType.Success);
            else
                counter(annotation.value(), MetircType.Failure);
        }

    }

    private void throwingProcess( TranMetric annotation ) {
        counter(annotation.value(), MetircType.Throwing);
    }

    private void counter( String name, MetircType type, boolean isQueued ) {
        if (MetircType.Total.compareTo(type) == 0) {
            counterService.increment(StrKit.join(".", name, type.name()));
        }
        else if (MetircType.Queued.compareTo(type) == 0) {
            counterService.increment(StrKit.join(".", name, type.name()));
        }
        else if (MetircType.Processing.compareTo(type) == 0) {
            counterService.increment(StrKit.join(".", name, type.name()));
            if (isQueued)
                counterService.decrement(StrKit.join(".", name, MetircType.Queued.name()));
        }
        else if (MetircType.Success.compareTo(type) == 0) {
            counterService.increment(StrKit.join(".", name, type.name()));
            counterService.decrement(StrKit.join(".", name, MetircType.Processing.name()));

        }
        else if (MetircType.Failure.compareTo(type) == 0) {
            counterService.increment(StrKit.join(".", name, type.name()));
            counterService.decrement(StrKit.join(".", name, MetircType.Processing.name()));

        }
        else if (MetircType.Throwing.compareTo(type) == 0) {
            counterService.increment(StrKit.join(".", name, type.name()));
            counterService.decrement(StrKit.join(".", name, MetircType.Processing.name()));
        }

    }

    private void counter( String name, MetircType type ) {
        counter(name, type, true);
    }

}
