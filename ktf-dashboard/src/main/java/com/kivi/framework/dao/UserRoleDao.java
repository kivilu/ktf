package com.kivi.framework.dao;

import java.util.List;

import com.kivi.framework.vo.UserRoleVO;

/**
 */
public interface UserRoleDao {

	/**
	 * 增加用户角色
	 * 
	 * @param userRole
	 */
	void addUserRole(UserRoleVO userRole);

	/**
	 * 删除用户的关联角色
	 * 
	 * @param userId
	 * @return
	 */
	int deleteUserRole(Long userId);
	
	/**
	 * 根据条件查询
	 * @param userRole
	 * @return
	 */
	List<UserRoleVO> listUserRole( UserRoleVO userRole);
}
