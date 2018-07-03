package com.kivi.framework.dao;

import java.util.List;
import java.util.Set;

import com.kivi.framework.vo.web.MenuNode;
import com.kivi.framework.vo.web.ResourceVO;
import com.kivi.framework.vo.web.ZTreeNode;

/**
 */
public interface ResourceDao {
	
	/**
     * 根据主键查询
     * 
     * @param code
     * @return
     */
	ResourceVO getResourceByKey( Long id );
	

    /**
     * 根据资源代码查询
     * 
     * @param code
     * @return
     */
	ResourceVO getResourceByCode( String code );

    /**
     * 更新
     * 
     * @param entity
     * @return
     */
    ResourceVO updateNotNull( ResourceVO vo );

    /**
     * 保存
     * 
     * @param entity
     * @return
     */
    ResourceVO saveNotNull( ResourceVO vo );

    /**
     * 删除菜单
     *
     */
    void delMenu( Long menuId );

    /**
     * 删除菜单包含所有子菜单
     *
     */
    void delMenuContainSubMenus( Long menuId );

    /**
     * 获取菜单列表树
     * 
     * @return
     */
    List<ZTreeNode> menuTreeList();

    /**
     * 根据条件查询菜单
     */
    List<Long> getMenuIdsByRoleId( Long roleId );

    /**
     * 获取菜单列表树
     *
     * @return
     */
    List<ZTreeNode> menuTreeListByMenuIds( List<Long> menuIds );

    /**
     * 根据名称和级别模糊查询菜单
     * 
     * @param name
     * @param level
     * @return
     */
    List<ResourceVO> selectResources( String name, Short level );

    /**
     * 根据查询条件
     * 
     * @param userId
     * @return
     */
    public List<ResourceVO> listResources( ResourceVO query );
    
    /**
     * 根据ids查询资源名称
     * 
     * @param ids
     * @return
     */
    public List<MenuNode> listResourceByIds( List<Long> ids );

    /**
     * 根据ids查询资源名称
     * 
     * @param ids
     * @return
     */
    public List<String> listResourceNameByIds( Long[] ids );


    /**
     * 根据角色ID查询权限列表
     * 
     * @param rid
     * @return
     */
    public List<ResourceVO> listRoleResources( Long rid );

    /**
     * 根据角色id获取权限URL
     * 
     * @param rid
     * @return
     */
    Set<String> selectPermissionUrl( Long rid );

    Set<String> selectPermissions( Long userId );
}
