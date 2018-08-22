package com.kivi.framework.web.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.async.DeferredResult;

import com.alibaba.fastjson.JSON;

public class KtfAsyncResult<T> extends DeferredResult<String> {
    private static final Logger log = LoggerFactory.getLogger(KtfAsyncResult.class);

    protected final String      msgId;

    public KtfAsyncResult( String msgId ) {
        super();
        this.msgId = msgId;
    }

    public KtfAsyncResult( String msgId, Long timeout ) {
        super(timeout);
        this.msgId = msgId;
    }

    @Override
    public boolean setResult( String result ) {
        log.trace("响应结果：{}", result);
        return super.setResult(result);
    }

    @Override
    public boolean setErrorResult( Object result ) {
        if (log.isTraceEnabled())
            log.trace("响应结果：{}", JSON.toJSONString(result));
        return super.setErrorResult(result);
    }

    public boolean setResultObject( Object result ) {

        String json = JSON.toJSONString(result);
        log.trace("响应结果：{}", json);

        return super.setResult(json);
    }

    public String getMsgId() {
        return msgId;
    }

    @Override
    public String toString() {
        return "KtfAsyncResult-" + msgId;
    }

}
