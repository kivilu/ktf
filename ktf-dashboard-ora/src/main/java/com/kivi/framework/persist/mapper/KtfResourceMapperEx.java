package com.kivi.framework.persist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kivi.framework.vo.MenuNode;
import com.kivi.framework.vo.ResourceVO;
import com.kivi.framework.vo.ZTreeNode;

public interface KtfResourceMapperEx {

    /**
     * 获取菜单列表树
     *
     * @return
     */
    List<ZTreeNode> menuTreeList();

    List<Long> getMenuIdsByRoleId( @Param( "roleId" ) Long roleId );

    List<ZTreeNode> menuTreeListByMenuIds( List<Long> list );

    /**
     * 根据IDs查询资源名称
     * 
     * @param ids
     * @return
     */
    List<MenuNode> listResourceByIds( List<Long> list );

    /**
     * 根据IDs查询资源名称
     * 
     * @param ids
     * @return
     */
    List<String> listResourceNameByIds( List<Long> list );

    /**
     * 根据用户查询资源列表
     * 
     * @param map
     * @return
     */
    // public List<ResourceVO> listUserResources( Map<String, Object> map );

    /**
     * 根据角色Id查询资源列表
     * 
     * @param rid
     * @return
     */
    public List<ResourceVO> listRoleResources( Long rid );

    /**
     * 根据条件查询菜单
     *
     * @return
     */
    List<ResourceVO> selectResources( @Param( "condition" ) String condition, @Param( "level" ) Short level );

    /**
     * 获取资源url通过角色id
     *
     */
    List<String> getResUrlsByRoleId( Long roleId );

}
