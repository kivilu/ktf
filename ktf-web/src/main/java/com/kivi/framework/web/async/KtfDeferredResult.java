package com.kivi.framework.web.async;

import com.alibaba.fastjson.JSON;

public class KtfDeferredResult extends KtfAsyncResult<String> {

    public KtfDeferredResult( Long msgId ) {
        super(msgId);
    }

    public KtfDeferredResult( Long msgId, Long timeout ) {
        super(msgId, timeout);
    }

    @Override
    public boolean setResultObject( String result ) {
        return super.setResultObject(result);
    }

    public boolean setResultRawObject( Object result ) {
        String json = JSON.toJSONString(result);
        return super.setResult(json);
    }

}
