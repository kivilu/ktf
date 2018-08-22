package com.kivi.framework.persist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface KtfApplicationMapperEx {

    List<Short> listApplicationSlotId( @Param( "name" ) String name );
}
