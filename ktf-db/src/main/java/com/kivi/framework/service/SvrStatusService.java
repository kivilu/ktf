package com.kivi.framework.service;

import com.kivi.framework.dto.SvrStatusReqDTO;
import com.kivi.framework.dto.SvrStatusRspDTO;
import com.kivi.framework.dto.warapper.WarpReqDTO;
import com.kivi.framework.dto.warapper.WarpRspDTO;

/**
 * 应用服务状态
 * 
 * @author Eric
 *
 */
public interface SvrStatusService {

    /**
     * 获取英语状态
     * 
     * @param req
     * @return
     */
    WarpRspDTO<SvrStatusRspDTO> status( WarpReqDTO<SvrStatusReqDTO> req );
}
