package com.kivi.framework.web.shiro.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.kivi.framework.util.kit.StrKit;
import com.kivi.framework.vo.web.UserVO;
import com.kivi.framework.web.shiro.factory.IShiro;
import com.kivi.framework.web.shiro.factory.ShiroFactroy;
import com.kivi.framework.web.shiro.util.ShiroKit;
import com.kivi.framework.web.shiro.vo.ShiroUserVO;

public class ShiroDbRealm extends AuthorizingRealm {

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo( AuthenticationToken authcToken )
            throws AuthenticationException {
        IShiro shiroFactory = ShiroFactroy.me();
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        UserVO user = shiroFactory.user(token.getUsername());
        ShiroUserVO shiroUser = shiroFactory.shiroUser(user);
        SimpleAuthenticationInfo info = shiroFactory.info(shiroUser, user, super.getName());
        return info;
    }

    /**
     * 权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo( PrincipalCollection principals ) {
        IShiro shiroFactory = ShiroFactroy.me();
        ShiroUserVO shiroUser = (ShiroUserVO) principals.getPrimaryPrincipal();
        List<Long> roleList = shiroUser.getRoleList();

        Set<String> permissionSet = new HashSet<>();
        Set<String> roleNameSet = new HashSet<>();

        for (Long roleId : roleList) {
            Set<String> permissions = shiroFactory.findPermissionsByRoleId(roleId);
            if (permissions != null) {
                for (String permission : permissions) {
                    if (StrKit.isNotEmpty(permission)) {
                        permissionSet.add(permission);
                    }
                }
            }
            String roleName = shiroFactory.findRoleNameByRoleId(roleId);
            roleNameSet.add(roleName);
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionSet);
        info.addRoles(roleNameSet);
        return info;
    }

    /**
     * 设置认证加密方式
     */
    @Override
    public void setCredentialsMatcher( CredentialsMatcher credentialsMatcher ) {
        HashedCredentialsMatcher md5CredentialsMatcher = new HashedCredentialsMatcher();
        md5CredentialsMatcher.setHashAlgorithmName(ShiroKit.hashAlgorithmName);
        md5CredentialsMatcher.setHashIterations(ShiroKit.hashIterations);
        super.setCredentialsMatcher(md5CredentialsMatcher);
    }
}
