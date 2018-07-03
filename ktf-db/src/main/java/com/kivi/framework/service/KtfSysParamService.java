package com.kivi.framework.service;

import com.kivi.framework.vo.KtfSysParamVO;

public interface KtfSysParamService {

    /**
     * 获取参数值
     * 
     * @param customCode
     * @param bizCode
     * @param paramCode
     * @return
     */
    String getParamVal( String customCode, String bizCode, String paramCode );

    /**
     * 获取整数参数值
     * 
     * @param customCode
     * @param bizCode
     * @param paramCode
     * @return
     */
    Integer getParamValInt( String customCode, String bizCode, String paramCode );

    /**
     * 获取Long参数值
     * 
     * @param customCode
     * @param bizCode
     * @param paramCode
     * @return
     */
    Long getParamValLong( String customCode, String bizCode, String paramCode );

    /**
     * 保存参数
     * 
     * @param vo
     * @return
     */
    KtfSysParamVO saveParam( KtfSysParamVO vo );

    /**
     * 更新参数
     * 
     * @param vo
     * @return
     */
    KtfSysParamVO updateParam( KtfSysParamVO vo );
}
