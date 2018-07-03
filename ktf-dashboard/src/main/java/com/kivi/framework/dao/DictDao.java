package com.kivi.framework.dao;

import java.util.List;
import java.util.Map;

import com.kivi.framework.vo.page.PageInfoKtf;
import com.kivi.framework.vo.page.PageReqVO;
import com.kivi.framework.vo.web.DictVO;

public interface DictDao {

    /**
     * 根据ID获取字段对象
     * 
     * @param id
     * @return
     */
    DictVO getDict( Long id );

    /**
     * 查询子字典
     * 
     * @param pid
     * @return
     */
    List<DictVO> listSubDict( Long pid );

    /**
     * 添加字典
     */
    void addDict( String dictName, String dictTips, String dictValues );

    /**
     * 编辑字典
     */
    void editDict( Long dictId, String dictName, String dictTips, String dicts );

    /**
     * 删除字典
     */
    void delteDict( Long dictId );

    /**
     * 根据编码获取词典列表
     *
     * @param code
     * @return
     */
    List<DictVO> selectByName( String name );

    /**
     * 根据编码获取子词典列表
     * 
     * @param name
     * @return
     */
    List<Map<String, Object>> listDictValues( String name );

    /**
     * 查询字典列表
     *
     */
    PageInfoKtf<DictVO> listPage( String conditiion, PageReqVO pageReq );

}
