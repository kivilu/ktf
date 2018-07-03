package com.kivi.framework.dao;

import com.kivi.framework.vo.OperationLogVO;
import com.kivi.framework.vo.page.PageInfoKtf;
import com.kivi.framework.vo.page.PageReqVO;

public interface OperationLogDao  {

    PageInfoKtf<OperationLogVO> listPage( String beginTime,
            String endTime, String logName, String logType,
            PageReqVO pageReq );

    void deleteAll();
}
