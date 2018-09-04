package com.kivi.framework.web.shiro.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheType;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kivi.framework.cache.CacheKit;
import com.kivi.framework.component.SpringContextHolder;
import com.kivi.framework.constant.enums.KtfStatus;
import com.kivi.framework.dao.ResourceDao;
import com.kivi.framework.util.Convert;
import com.kivi.framework.util.kit.CollectionKit;
import com.kivi.framework.vo.UserVO;
import com.kivi.framework.web.constant.factory.ConstantFactory;
import com.kivi.framework.web.shiro.cache.ShiroRedisCacheManager;
import com.kivi.framework.web.shiro.token.helper.ITokenCache;
import com.kivi.framework.web.shiro.token.helper.TokenCacheFactory;
import com.kivi.framework.web.shiro.vo.ShiroUserVO;

@Component
@ConditionalOnProperty( prefix = "ktf.shiro", name = "enabled", havingValue = "true", matchIfMissing = false )
@Transactional( readOnly = true )
public class ShiroFactroy implements IShiro {

    @Autowired
    private ResourceDao resourceDao;

    public static IShiro me() {
        return SpringContextHolder.getBean(IShiro.class);
    }

    @Override
    public UserVO user( String account ) {

        ITokenCache tokenCache = TokenCacheFactory.me().tokenCache();
        // 访问一次，计数一次
        int count = tokenCache.getAndIncrementLoginCount(account);
        // 计数大于5时，设置用户被锁定一小时
        if (count >= 5) {
            // TODO: 修改为配置
            tokenCache.setLoginFlagExpiration(account, 7200); // 锁定2小时
        }
        if (tokenCache.getLoginLockFlag(account)) {
            throw new DisabledAccountException("由于登录失败次数大于5次，帐号已经禁止登录！");
        }

        UserVO user = ConstantFactory.me().getUser(account);

        // 账号不存在
        if (null == user) {
            throw new CredentialsException();
        }
        // 账号被冻结
        if (user.getStatus() != KtfStatus.ENABLED.code) {
            throw new LockedAccountException("账号已被冻结");
        }
        return user;
    }

    @Override
    public ShiroUserVO shiroUser( UserVO user ) {
        ShiroUserVO shiroUser = new ShiroUserVO();

        shiroUser.setId(user.getId()); // 账号id
        shiroUser.setAccount(user.getAccount());// 账号
        shiroUser.setDeptId(user.getDeptId()); // 部门id
        shiroUser.setDeptName(ConstantFactory.me().getDeptName(user.getDeptId()));// 部门名称
        shiroUser.setName(user.getName()); // 用户名称

        Long[] roleArray = Convert.toLongArray(true, user.getRoleId());
        List<String> roleNameList = new ArrayList<String>();
        for (long roleId : roleArray) {
            roleNameList.add(ConstantFactory.me().getSingleRoleName(roleId));
        }
        shiroUser.setRoleNames(roleNameList);
        shiroUser.setRoleList(CollectionKit.newArrayList(roleArray));

        return shiroUser;
    }

    @Override
    public SimpleAuthenticationInfo info( ShiroUserVO shiroUser, UserVO user, String realmName ) {
        String credentials = user.getPassword();
        // 密码加盐处理
        String source = user.getSalt();
        ByteSource credentialsSalt = new Md5Hash(source);

        return new SimpleAuthenticationInfo(shiroUser, credentials, credentialsSalt, realmName);
    }

    @Override
    public org.apache.shiro.cache.CacheManager getShiroCacheManager() {

        if (CacheType.EHCACHE.compareTo(CacheKit.me().getCacheType()) == 0) {
            org.apache.shiro.cache.ehcache.EhCacheManager ehCacheManager = new EhCacheManager();
            ehCacheManager.setCacheManager(CacheKit.me().getEhCacheManager().getCacheManager());
            return ehCacheManager;
        }
        else if (CacheType.REDIS.compareTo(CacheKit.me().getCacheType()) == 0) {
            ShiroRedisCacheManager redisCacheManager = new ShiroRedisCacheManager(CacheKit.me().getRedisCacheManager());
            return redisCacheManager;
        }

        return null;
    }

    @Override
    public Set<String> findPermissionsByRoleId( Long roleId ) {
        Set<String> resUrls = resourceDao.selectPermissionUrl(roleId);
        return resUrls;
    }

    @Override
    public String findRoleNameByRoleId( Long roleId ) {
        return ConstantFactory.me().getSingleRoleTip(roleId);
    }

}
