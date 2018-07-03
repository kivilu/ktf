package com.kivi.framework.dao.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kivi.framework.cache.constant.KtfCache;
import com.kivi.framework.dao.RoleDao;
import com.kivi.framework.dao.RoleResourceDao;
import com.kivi.framework.db.dao.BaseDao;
import com.kivi.framework.persist.mapper.KtfRoleMapperEx;
import com.kivi.framework.persist.model.KtfRole;
import com.kivi.framework.persist.model.KtfRoleResource;
import com.kivi.framework.util.Convert;
import com.kivi.framework.util.kit.BeanKit;
import com.kivi.framework.util.kit.CollectionKit;
import com.kivi.framework.util.kit.DateTimeKit;
import com.kivi.framework.util.kit.StrKit;
import com.kivi.framework.vo.web.RoleVO;
import com.kivi.framework.vo.web.ZTreeNode;

@Repository
public class RoleDaoImpl extends BaseDao<KtfRole> implements RoleDao {

    @Autowired
    private RoleResourceDao roleResourceDao;

    @Autowired
    private KtfRoleMapperEx ktfRoleMapperEx;

    @CacheEvict( value = KtfCache.Dashboard.ROLE_VO, allEntries = true )
    @Override
    @Transactional
    public void deleteRole( Long roleId ) {
        // 删除角色
        super.delete(roleId);

        // 删除角色资源
        roleResourceDao.deleteByRoleId(roleId);
    }

    @Override
    public List<RoleVO> listRoleByUserId( Long id ) {
        return ktfRoleMapperEx.listRoleByUserId(id);
    }

    @Override
    public List<RoleVO> listRoleWithSelected( Long id ) {
        return ktfRoleMapperEx.listRoleWithSelected(id);
    }

    @Override
    public List<String> listRoleNameByIds( Long[] ids ) {
        return ktfRoleMapperEx.listRoleNameByIds(Arrays.asList(ids));
    }

    @Cacheable( value = KtfCache.Dashboard.ROLE_VO, key = "#id" )
    @Override
    public RoleVO getRoleByPk( Long id ) {
        KtfRole entity = super.selectByKey(id);
        RoleVO vo = new RoleVO();
        BeanKit.copyProperties(entity, vo);
        return vo;
    }

    @Override
    public List<RoleVO> selectRoles( String name ) {
        List<RoleVO> list = ktfRoleMapperEx.selectRoles(name);

        return list;
    }

    @CachePut( value = KtfCache.Dashboard.ROLE_VO, key = "#vo.id" )
    @Override
    public RoleVO saveNotNull( RoleVO vo ) {
        KtfRole entity = new KtfRole();
        BeanKit.copyProperties(vo, entity);
        entity.setGmtCreate(DateTimeKit.date());
        entity.setGmtUpdate(entity.getGmtCreate());
        super.saveNotNull(entity);
        vo.setId(entity.getId());

        return vo;
    }

    @CacheEvict( value = KtfCache.Dashboard.ROLE_VO, key = "#vo.id" )
    @Override
    public RoleVO updateNotNull( RoleVO vo ) {
        KtfRole entity = new KtfRole();
        BeanKit.copyProperties(vo, entity);
        entity.setGmtUpdate(DateTimeKit.date());
        super.updateNotNull(entity);

        return vo;
    }

    @Override
    @Transactional( readOnly = false )
    public void setAuthority( Long roleId, String ids ) {

        // 删除该角色所有的权限
        roleResourceDao.deleteByRoleId(roleId);

        roleResourceDao.addRoleResources(roleId, ids);
    }

    @Override
    public List<ZTreeNode> roleTreeList() {
        return ktfRoleMapperEx.roleTreeList();
    }

    @Override
    public List<ZTreeNode> roleTreeListByRoleId( String[] roleIds ) {
        List<Long> list = CollectionKit.newArrayList(Convert.toLongArray(true, roleIds));
        return ktfRoleMapperEx.roleTreeListByRoleId(list);
    }
}
