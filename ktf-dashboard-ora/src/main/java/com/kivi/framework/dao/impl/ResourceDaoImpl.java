package com.kivi.framework.dao.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kivi.framework.dao.ResourceDao;
import com.kivi.framework.dao.RoleResourceDao;
import com.kivi.framework.db.dao.BaseDao;
import com.kivi.framework.persist.mapper.KtfResourceMapperEx;
import com.kivi.framework.persist.model.KtfResource;
import com.kivi.framework.util.Convert;
import com.kivi.framework.util.kit.BeanKit;
import com.kivi.framework.util.kit.DateTimeKit;
import com.kivi.framework.vo.KtfNoticeVO;
import com.kivi.framework.vo.MenuNode;
import com.kivi.framework.vo.ResourceVO;
import com.kivi.framework.vo.ZTreeNode;

@Repository
public class ResourceDaoImpl extends BaseDao<KtfResource> implements ResourceDao {

    @Autowired
    private KtfResourceMapperEx ktfResourceMapperEx;
    @Autowired
    private RoleResourceDao     roleResourceDao;

    /*
     * @Override public List<ResourceVO> listUserResources( Long userId ) {
     * 
     * Map<String, Object> map = new HashMap<String, Object>();
     * map.put("userId", userId);
     * 
     * return ktfResourceMapperEx.listUserResources(map); }
     */

    @Override
    public List<ResourceVO> listRoleResources( Long rid ) {
        return ktfResourceMapperEx.listRoleResources(rid);
    }

    @Override
    public Set<String> selectPermissionUrl( Long rid ) {
        List<String> list = ktfResourceMapperEx.getResUrlsByRoleId(rid);
        Set<String> permissions = new HashSet<String>();
        permissions.addAll(list);
        return permissions;
    }

    @Override
    public List<MenuNode> listResourceByIds( List<Long> ids ) {
        return ktfResourceMapperEx.listResourceByIds(ids);
    }

    @Override
    public Set<String> selectPermissions( Long userId ) {
        /*
         * List<ResourceVO> list = this.listUserResources(userId);
         * 
         * Set<String> permissions = new HashSet<String>(); for (ResourceVO r :
         * list) { permissions.add(r.getUrl()); }
         * 
         * return permissions;
         */
        return null;
    }

    @Override
    public List<String> listResourceNameByIds( Long[] ids ) {

        return ktfResourceMapperEx.listResourceNameByIds(Arrays.asList(ids));
    }

    @Override
    public ResourceVO getResourceByCode( String code ) {

        KtfResource qryEnt = new KtfResource();
        qryEnt.setCode(code);
        KtfResource r =  super.selectOne(qryEnt);
        if(r==null)
        	return null;
        
        return Convert.convertObject(r, ResourceVO.class);
    }

    @Override
    public ResourceVO updateNotNull( ResourceVO vo ) {
        KtfResource entity = new KtfResource();

        BeanKit.copyProperties(vo, entity);

        super.updateNotNull(entity);

        return vo;
    }

    @Override
    public ResourceVO saveNotNull( ResourceVO vo ) {
        KtfResource entity = new KtfResource();

        BeanKit.copyProperties(vo, entity);
        entity.setGmtCreate(DateTimeKit.date());
        entity.setGmtUpdate(entity.getGmtCreate());

        super.saveNotNull(entity);

        vo.setId(entity.getId());

        return vo;
    }

    @Override
    public List<ResourceVO> selectResources( String name, Short level ) {
        // List<Map<String, Object>> result = new ArrayList<>();
        List<ResourceVO> list = ktfResourceMapperEx.selectResources(name, level);

        return list;
    }

    @Transactional
    @Override
    public void delMenu( Long menuId ) {
        // 删除菜单
        this.mapper.deleteByPrimaryKey(menuId);

        // 删除关联的relation
        roleResourceDao.deleteByResourceId(menuId);

    }

    @Transactional
    @Override
    public void delMenuContainSubMenus( Long menuId ) {

        KtfResource entity = super.selectByKey(menuId);

        // 删除本菜单
        this.delete(menuId);

        // 删除子菜单
        KtfResource qry = new KtfResource();
        qry.setCode(entity.getCode());
        List<KtfResource> list = super.selectByEntity(entity);

        for (KtfResource temp : list) {
            delMenu(temp.getId());
        }

    }

    @Override
    public List<ZTreeNode> menuTreeList() {

        return ktfResourceMapperEx.menuTreeList();
    }

    @Override
    public List<ZTreeNode> menuTreeListByMenuIds( List<Long> menuIds ) {
        return ktfResourceMapperEx.menuTreeListByMenuIds(menuIds);
    }

    @Override
    public List<Long> getMenuIdsByRoleId( Long roleId ) {
        return ktfResourceMapperEx.getMenuIdsByRoleId(roleId);
    }

	@Override
	public ResourceVO getResourceByKey(Long id) {
		KtfResource r = super.selectByKey(id);
		
		return Convert.convertObject(r, ResourceVO.class);
	}

	@Override
	public List<ResourceVO> listResources(ResourceVO query) {
		KtfResource entity = new KtfResource();
		
		if( query !=null){
			BeanKit.copyProperties(query, entity);
		}
		
		List<KtfResource> list  = super.selectByEntity(entity);
		
		 List<ResourceVO> result = list.stream().map(r->{return Convert.convertObject(r, ResourceVO.class);})
	        		.collect(Collectors.toList());
		
		return result;
	}

}
