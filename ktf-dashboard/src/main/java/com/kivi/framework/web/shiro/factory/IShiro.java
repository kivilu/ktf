package com.kivi.framework.web.shiro.factory;

import java.util.Set;

import org.apache.shiro.authc.SimpleAuthenticationInfo;

import com.kivi.framework.vo.UserVO;
import com.kivi.framework.web.shiro.vo.ShiroUserVO;

/**
 * 定义shirorealm所需数据的接口
 *
 */
public interface IShiro {

    /**
     * 根据账号获取登录用户
     *
     * @param account
     *            账号
     */
    UserVO user( String account );

    /**
     * 根据系统用户获取Shiro的用户
     *
     * @param user
     *            系统用户
     */
    ShiroUserVO shiroUser( UserVO user );

    /**
     * 获取权限列表通过角色id
     *
     * @param roleId
     *            角色id
     */
    Set<String> findPermissionsByRoleId( Long roleId );

    /**
     * 根据角色id获取角色名称
     *
     * @param roleId
     *            角色id
     */
    String findRoleNameByRoleId( Long roleId );

    /**
     * 获取shiro的认证信息
     */
    SimpleAuthenticationInfo info( ShiroUserVO shiroUser, UserVO user, String realmName );

    org.apache.shiro.cache.CacheManager getShiroCacheManager();

}
