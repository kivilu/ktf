package com.kivi.framework.dao;

import com.kivi.framework.vo.OperationLogVO;
import com.kivi.framework.vo.page.PageInfoKtf;
import com.kivi.framework.vo.page.PageReqVO;

public interface KtfOperationLogDao {

    /**
     * 根据主键查询
     * 
     * @param id
     * @return
     */
    OperationLogVO getByKey( Long id );

    /**
     * 分页查询
     * 
     * @param beginTime
     * @param endTime
     * @param logName
     * @param logType
     * @param pageReq
     * @return
     */
    PageInfoKtf<OperationLogVO> listPage( String beginTime,
            String endTime, String logName, String logType,
            PageReqVO pageReq );

    /**
     * 删除全部记录
     */
    void deleteAll();
}
