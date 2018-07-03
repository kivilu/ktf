package com.kivi.framework.persist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kivi.framework.persist.model.KtfLoginLog;

public interface KtfLoginLogMapperEx {

    /**
     * 获取登录日志
     *
     */
    List<KtfLoginLog> getLoginLogs( @Param( "beginTime" ) String beginTime, @Param( "endTime" ) String endTime,
            @Param( "logName" ) String logName, @Param( "orderByField" ) String orderByField,
            @Param( "isAsc" ) Boolean isAsc );

}
