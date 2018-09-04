package com.kivi.framework.persist.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kivi.framework.vo.DictVO;

public interface KtfDictMapperEx {

    /**
     * 根据编码获取词典列表
     *
     * @param code
     * @return
     */
    List<DictVO> selectByName( @Param( "name" ) String name );

    /**
     * 根据编码获取子词典列表
     * 
     * @param name
     * @return
     */
    List<Map<String, Object>> listDictValues( @Param( "name" ) String name );

    /**
     * 查询字典列表
     *
     */
    List<DictVO> list( @Param( "condition" ) String conditiion );
}
