package com.kivi.framework.web.async;

import com.kivi.framework.constant.GlobalErrorConst;
import com.kivi.framework.dto.BaseRsp;
import com.kivi.framework.service.ITimeoutService;

public class KtfTimeoutRunnable<T> implements Runnable {

    private final KtfAsyncResult<T> deferredResult;
    private final ITimeoutService   timeoutService;

    public KtfTimeoutRunnable( KtfAsyncResult<T> deferredResult, ITimeoutService timeoutService ) {
        this.deferredResult = deferredResult;
        this.timeoutService = timeoutService;
    }

    @Override
    public void run() {
        BaseRsp<String> rsp = new BaseRsp<String>();
        rsp.setRspCode(GlobalErrorConst.E_REQ_TIMEOUT);
        rsp.setRspDesc("请求超时");
        rsp.setRspBody("");

        if (timeoutService != null)
            timeoutService.onTimeout(deferredResult.getMsgId());

        deferredResult.setErrorResult(rsp);
    }

}
