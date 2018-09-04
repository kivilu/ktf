package com.kivi.framework.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kivi.framework.dao.UserRoleDao;
import com.kivi.framework.db.dao.BaseDao;
import com.kivi.framework.persist.model.KtfUserRole;
import com.kivi.framework.util.Convert;
import com.kivi.framework.vo.KtfNoticeVO;
import com.kivi.framework.vo.UserRoleVO;

import tk.mybatis.mapper.entity.Example;

@Repository
public class UserRoleDaoImpl extends BaseDao<KtfUserRole> implements UserRoleDao {

	@Override
	@Transactional
	public void addUserRole(UserRoleVO userRole) {
		// 删除
		this.deleteUserRole(userRole.getUserId());
		// 添加
		String[] roleids = userRole.getRoleId().split(",");
		for (String roleId : roleids) {
			KtfUserRole u = new KtfUserRole();
			u.setUserId(userRole.getUserId());
			u.setRoleId(Long.parseLong(roleId));
			mapper.insert(u);
		}

	}

	@Override
	public int deleteUserRole(Long userId) {
		// 删除
		Example example = new Example(KtfUserRole.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("user_id", userId);
		return mapper.deleteByExample(example);
	}

	@Override
	public List<UserRoleVO> listUserRole(UserRoleVO userRole) {
		
		KtfUserRole entity = Convert.convertObject(userRole, KtfUserRole.class) ;
		List<KtfUserRole> list = super.selectByEntity(entity);
		
		 List<UserRoleVO> result = list.stream().map(r->{return Convert.convertObject(r, UserRoleVO.class);})
	        		.collect(Collectors.toList());
		
		return result;
	}

}
