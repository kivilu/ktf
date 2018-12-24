package com.kivi.framework.dao;

import com.kivi.framework.vo.LoginLogVO;
import com.kivi.framework.vo.PageInfoKtf;
import com.kivi.framework.vo.page.PageReqVO;

public interface LoginLogDao {

    PageInfoKtf<LoginLogVO> listPage( String beginTime, String endTime,
            String logName, PageReqVO pageReq );

    void deleteAll();
}
