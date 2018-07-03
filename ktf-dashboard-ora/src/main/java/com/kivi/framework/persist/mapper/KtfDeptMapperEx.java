package com.kivi.framework.persist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kivi.framework.vo.web.DeptVO;
import com.kivi.framework.vo.web.ZTreeNode;

public interface KtfDeptMapperEx {

    /**
     * 获取ztree的节点列表
     *
     */
    List<ZTreeNode> tree();

    /**
     * 根据条件查询
     * 
     */
    List<DeptVO> list( @Param( "condition" ) String condition );
}
