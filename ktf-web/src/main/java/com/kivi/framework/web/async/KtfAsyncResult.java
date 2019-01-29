package com.kivi.framework.web.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.async.DeferredResult;

import com.alibaba.fastjson.JSON;

import io.swagger.annotations.ApiModel;

@ApiModel( value = "KtfAsyncResult", description = "异步响应对象" )
public class KtfAsyncResult<T> extends DeferredResult<T> {
    private static final Logger log = LoggerFactory.getLogger(KtfAsyncResult.class);

    protected final Long        msgId;

    public KtfAsyncResult( Long msgId ) {
        super();
        this.msgId = msgId;
    }

    public KtfAsyncResult( Long msgId, Long timeout ) {
        super(timeout);
        this.msgId = msgId;
    }

    @Override
    public boolean setErrorResult( Object result ) {
        if (log.isTraceEnabled())
            log.trace("响应结果：{}", JSON.toJSONString(result));
        return super.setErrorResult(result);
    }

    @Override
    public boolean setResult( T result ) {

        if (log.isTraceEnabled()) {
            String json = JSON.toJSONString(result);
            log.trace("响应结果：{}", json);
        }

        return super.setResult(result);
    }

    public Long getMsgId() {
        return msgId;
    }

    @Override
    public String toString() {
        return "KtfAsyncResult-" + msgId;
    }

}
