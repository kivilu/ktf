package com.kivi.framework.web.async;

public class KtfDeferredResult extends KtfAsyncResult<String> {

    public KtfDeferredResult( String msgId ) {
        super(msgId);
    }

    public KtfDeferredResult( String msgId, Long timeout ) {
        super(msgId, timeout);
    }

    @Override
    public boolean setResultObject( Object result ) {
        return super.setResultObject(result);
    }

}
