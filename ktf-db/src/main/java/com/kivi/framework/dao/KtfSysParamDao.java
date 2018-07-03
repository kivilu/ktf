package com.kivi.framework.dao;

import com.kivi.framework.vo.KtfSysParamVO;

public interface KtfSysParamDao  {

    /**
     * 获取系统参数
     * 
     * @param customCode
     *            客户代码
     * @param bizCode
     *            业务代码
     * @param paramCode
     *            参数代码
     * @return
     */
    KtfSysParamVO getKtfSysParam( String customCode, String bizCode, String paramCode );

    /**
     * 列出业务相关的全部参数
     * 
     * @param customCode
     * @param bizCode
     * @return map JSON String
     */
    String listKtfSysParamValue( String customCode, String bizCode );
}
