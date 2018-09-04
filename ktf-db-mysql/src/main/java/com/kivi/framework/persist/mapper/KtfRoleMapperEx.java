package com.kivi.framework.persist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kivi.framework.vo.RoleVO;
import com.kivi.framework.vo.ZTreeNode;

public interface KtfRoleMapperEx {

    /**
     * 根据用户列出全部角色信息
     * 
     * @param id
     * @return
     */
    public List<RoleVO> listRoleByUserId( Long userId );

    /**
     * 返回全部角色列表，标记那个角色被用用户选中
     * 
     * @param id
     * @return
     */
    public List<RoleVO> listRoleWithSelected( Long userId );

    /**
     * 根据Ids对应的role名称
     * 
     * @param ids
     * @return
     */
    List<String> listRoleNameByIds( List<Long> list );

    /**
     * 根据条件查询角色列表
     * 
     * @param condition
     * @return
     */
    List<RoleVO> selectRoles( @Param( "condition" ) String condition );

    /**
     * 获取角色列表树
     *
     */
    List<ZTreeNode> roleTreeList();

    /**
     * 获取角色列表树
     *
     */
    List<ZTreeNode> roleTreeListByRoleId( List<Long> list );

}
