package com.kivi.framework.dao;

import java.util.List;

import com.kivi.framework.vo.RoleVO;
import com.kivi.framework.vo.ZTreeNode;

public interface RoleDao {

    /**
     * 根据主键查询
     * 
     * @param id
     * @return
     */
    RoleVO getRoleByPk( Long id );

    /**
     * 根据角色名称
     * 
     * @param name
     *            角色名称
     * @return
     */
    RoleVO getRoleByName( String name );

    /**
     * 保存角色
     * 
     * @param vo
     * @return
     */
    RoleVO saveNotNull( RoleVO vo );

    /**
     * 更新角色
     * 
     * @param vo
     * @return
     */
    RoleVO updateNotNull( RoleVO vo );

    /**
     * 根据Ids对应的role名称
     * 
     * @param ids
     * @return
     */
    List<String> listRoleNameByIds( Long[] ids );

    /**
     * 根据用户列出全部角色信息
     * 
     * @param id
     * @return
     */
    public List<RoleVO> listRoleByUserId( Long id );

    /**
     * 返回全部角色列表，标记那个角色被用用户选中
     * 
     * @param id
     * @return
     */
    public List<RoleVO> listRoleWithSelected( Long id );

    /**
     * 删除角色 同时删除角色资源表中的数据
     * 
     * @param roleid
     */
    public void deleteRole( Long roleid );

    /**
     * 根据角色名称模糊查询
     * 
     * @param name
     * @return
     */
    List<RoleVO> selectRoles( String name );

    /**
     * 设置角色权限
     * 
     * @param roleId
     * @param ids
     */
    void setAuthority( Long roleId, String ids );

    /**
     * 获取角色列表树
     *
     */
    List<ZTreeNode> roleTreeList();

    /**
     * 获取角色列表树
     *
     */
    List<ZTreeNode> roleTreeListByRoleId( String[] roleId );
}
