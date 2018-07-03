package com.kivi.framework.persist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kivi.framework.persist.model.KtfNotice;

public interface KtfNoticeMapperEx {

    List<KtfNotice> list( @Param( "condition" ) String condition );
}
