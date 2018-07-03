package com.kivi.framework.web.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.async.DeferredResult;

import com.alibaba.fastjson.JSON;

public class KtfDeferredResult extends DeferredResult<String> {
    private static final Logger log = LoggerFactory.getLogger(KtfDeferredResult.class);

    private final String        msgId;

    public KtfDeferredResult( String msgId ) {
        super();
        this.msgId = msgId;
    }

    public KtfDeferredResult( String msgId, Long timeout ) {
        super(timeout);
        this.msgId = msgId;
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
        return "KtfDeferredResult-" + msgId;
    }

}
