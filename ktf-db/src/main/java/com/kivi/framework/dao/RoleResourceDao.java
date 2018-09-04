package com.kivi.framework.dao;

import com.kivi.framework.vo.RoleResourceVO;

/**
 */
public interface RoleResourceDao {
    /**
     * 给role添加一个或多个resource
     * 
     * @param roleResources
     */
    void addRoleResources( RoleResourceVO roleResources );
    
    /**
     * 添加角色和菜单的映射关系
     * @param roleId
     * @param resourceIds
     */
    void addRoleResources( Long roleId, String resourceIds );

    /**
     * 根据角色ID删除关联关系
     * 
     * @param roleId
     * @return
     */
    int deleteByRoleId( Long roleId );

    /**
     * 根据资源ID删除关联关系
     * 
     * @param resourceId
     * @return
     */
    int deleteByResourceId( Long resourceId );
    
}
