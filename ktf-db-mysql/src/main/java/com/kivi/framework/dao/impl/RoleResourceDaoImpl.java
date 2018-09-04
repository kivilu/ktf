package com.kivi.framework.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kivi.framework.dao.RoleResourceDao;
import com.kivi.framework.db.dao.BaseDao;
import com.kivi.framework.persist.model.KtfRoleResource;
import com.kivi.framework.util.Convert;
import com.kivi.framework.util.kit.DateTimeKit;
import com.kivi.framework.util.kit.StrKit;
import com.kivi.framework.vo.RoleResourceVO;

import tk.mybatis.mapper.entity.Example;

@Repository
public class RoleResourceDaoImpl extends BaseDao<KtfRoleResource> implements RoleResourceDao {

    @Override
    @Transactional
    public void addRoleResources( RoleResourceVO roleResources ) {
        // 删除原权限
        this.deleteByRoleId(roleResources.getRoleId());

        // 添加
        if (StrKit.isNotEmpty(roleResources.getResourcesId())) {
            String[] resourcesArr = roleResources.getResourcesId().split(",");
            for (String resourcesId : resourcesArr) {
                KtfRoleResource r = new KtfRoleResource();
                r.setRoleId(roleResources.getRoleId());
                r.setResourceId(Long.parseLong(resourcesId));
                mapper.insert(r);
            }
        }
    }

    @Override
    public int deleteByRoleId( Long roleId ) {
        // 删除
        Example example = new Example(KtfRoleResource.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleId", roleId);

        return mapper.deleteByExample(example);
    }

    @Override
    public int deleteByResourceId( Long resourceId ) {
        // 删除
        Example example = new Example(KtfRoleResource.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("resourceId", resourceId);

        return mapper.deleteByExample(example);
    }

    @Override
    public KtfRoleResource saveNotNull( KtfRoleResource entity ) {
        entity.setGmtCreate(DateTimeKit.date());
        entity.setGmtUpdate(entity.getGmtCreate());
        return super.saveNotNull(entity);
    }

	@Override
	public void addRoleResources(Long roleId, String resourceIds) {

		// 添加新的权限
        for (Long id : Convert.toLongArray(true, StrKit.split(resourceIds, StrKit.COMMA))) {
            KtfRoleResource relation = new KtfRoleResource();
            relation.setRoleId(roleId);
            relation.setResourceId(id);
            super.saveNotNull(relation);
        }
		
	}

}
