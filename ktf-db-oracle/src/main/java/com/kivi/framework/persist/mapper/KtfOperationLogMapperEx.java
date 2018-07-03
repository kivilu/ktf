package com.kivi.framework.persist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kivi.framework.persist.model.KtfOperationLog;

public interface KtfOperationLogMapperEx {

    /**
     * 获取操作日志
     *
     */
    List<KtfOperationLog> getOperationLogs( @Param( "beginTime" ) String beginTime,
            @Param( "endTime" ) String endTime, @Param( "logName" ) String logName, @Param( "logType" ) String logType,
            @Param( "orderByField" ) String orderByField, @Param( "isAsc" ) boolean isAsc );
}
