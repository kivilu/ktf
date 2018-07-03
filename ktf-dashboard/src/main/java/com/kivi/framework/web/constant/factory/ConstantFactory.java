package com.kivi.framework.web.constant.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.kivi.framework.component.SpringContextHolder;
import com.kivi.framework.constant.enums.KtfStatus;
import com.kivi.framework.dao.DeptDao;
import com.kivi.framework.dao.DictDao;
import com.kivi.framework.dao.NoticeDao;
import com.kivi.framework.dao.ResourceDao;
import com.kivi.framework.dao.RoleDao;
import com.kivi.framework.dao.UserDao;
import com.kivi.framework.dao.UserRoleDao;
import com.kivi.framework.util.Convert;
import com.kivi.framework.util.kit.CollectionKit;
import com.kivi.framework.util.kit.ObjectKit;
import com.kivi.framework.util.kit.StrKit;
import com.kivi.framework.vo.KtfNoticeVO;
import com.kivi.framework.vo.web.DeptVO;
import com.kivi.framework.vo.web.DictVO;
import com.kivi.framework.vo.web.ResourceVO;
import com.kivi.framework.vo.web.RoleVO;
import com.kivi.framework.vo.web.UserRoleVO;
import com.kivi.framework.vo.web.UserVO;
import com.kivi.framework.web.constant.WebConst;

import tk.mybatis.mapper.entity.Example;

/**
 * 常量的生产工厂
 *
 */
@Component
@DependsOn( "springContextHolder" )
public class ConstantFactory implements IConstantFactory {

    private RoleDao                                  roleDao      = SpringContextHolder.getBean(RoleDao.class);
    private UserRoleDao                              userRoleDao  = SpringContextHolder.getBean(UserRoleDao.class);
    private UserDao                                  userDao      = SpringContextHolder.getBean(UserDao.class);
    private ResourceDao                              resourceDao  = SpringContextHolder.getBean(ResourceDao.class);
    private DeptDao									 deptDao = SpringContextHolder.getBean(DeptDao.class);
    private NoticeDao								noticeDao = SpringContextHolder.getBean(NoticeDao.class);
    private DictDao								dictDao = SpringContextHolder.getBean(DictDao.class);

    private static ConcurrentHashMap<String, String> dictMap      = new ConcurrentHashMap<>();

    public static IConstantFactory me() {
        return SpringContextHolder.getBean("constantFactory");
    }

    @Override
    public UserVO getUser( String account ) {

        return userDao.getByAccountWithPwd(account);
    }

    /**
     * 根据用户id获取用户名称
     *
     */
    @Override
    public String getUserNameById( Long userId ) {
    	UserVO user = userDao.getUserByPk(userId);
        if (user != null) {
            return user.getName();
        }
        else {
            return "--";
        }
    }

    /**
     * 根据用户id获取用户账号
     *
     */
    @Override
    public String getUserAccountById( Long userId ) {
    	UserVO user = userDao.getUserByPk(userId);
        if (user != null) {
            return user.getAccount();
        }
        else {
            return "--";
        }
    }

    @Override
    public String getRoleIdsByUserId( Long userId ) {
    	UserRoleVO vo = new UserRoleVO();
        List<UserRoleVO> list = userRoleDao.listUserRole(vo);

        StringBuilder builder = StrKit.builder();
        for (UserRoleVO ur : list) {
            builder.append(ur.getRoleId()).append(",");
        }

        return StrKit.removeSuffix(builder.toString(), ",");
    }

    /**
     * 通过角色ids获取角色名称
     */
    @Override
    // @Cacheable( value = KtfCache.ROLES_NAME_CACHE, key = "#roleIds" )
    public String getRoleName( String roleIds ) {
        if (StrKit.isBlank(roleIds))
            return "";
        Long[] roles = Convert.toLongArray(true, roleIds);

        List<String> names = roleDao.listRoleNameByIds(roles);

        return CollectionKit.join(names, ",");
    }

    /**
     * 通过角色id获取角色名称
     */
    @Override
    // @Cacheable( value = KtfCache.SINGLE_ROLE_NAME_CACHE, key = "#roleId" )
    public String getSingleRoleName( Long roleId ) {
        if (0 == roleId) {
            return "--";
        }
        RoleVO roleObj = roleDao.getRoleByPk(roleId);
        if (ObjectKit.isNotEmpty(roleObj) && ObjectKit.isNotEmpty(roleObj.getName())) {
            return roleObj.getName();
        }
        return "--";
    }

    /**
     * 通过角色id获取角色英文名称
     */
    @Override
    // @Cacheable( value = KtfCache.SINGLE_ROLE_TIP_CACHE, key = "#roleId" )
    public String getSingleRoleTip( Long roleId ) {
        if (0 == roleId) {
            return "--";
        }
        RoleVO roleObj = roleDao.getRoleByPk(roleId);
        if (ObjectKit.isNotEmpty(roleObj) && ObjectKit.isNotEmpty(roleObj.getName())) {
            return roleObj.getTips();
        }
        return "--";
    }

    /**
     * 获取部门名称
     */
    @Override
    // @Cacheable( value = KtfCache.DEPT_NAME_CACHE, key = "#deptId" )
    public String getDeptName( Long deptId ) {
        if (deptId == null)
            return "";

        DeptVO dept = deptDao.getDept(deptId);
        if (ObjectKit.isNotEmpty(dept) && ObjectKit.isNotEmpty(dept.getFullname())) {
            return dept.getFullname();
        }
        return "";
    }

    /**
     * 获取菜单的名称们(多个)
     */
    @Override
    public String getMenuNames( String menuIds ) {
        Long[] ids = Convert.toLongArray(true, menuIds);

        List<String> names = resourceDao.listResourceNameByIds(ids);

        return CollectionKit.join(names, ",");
    }

    /**
     * 获取菜单名称
     */
    @Override
    public String getMenuName( Long menuId ) {
        if (ObjectKit.isEmpty(menuId)) {
            return "--";
        }
        else {
            ResourceVO menu = resourceDao.getResourceByKey(menuId);
            if (menu == null) {
                return "--";
            }
            else {
                return menu.getName();
            }
        }
    }

    /**
     * 获取菜单名称通过编号
     */
    @Override
    public String getMenuNameByCode( String code ) {
        if (ObjectKit.isEmpty(code)) {
            return "";
        }
        else {
            ResourceVO menu = resourceDao.getResourceByCode(code);
            if (menu == null) {
                return "";
            }
            else {
                return menu.getName();
            }
        }
    }

    /**
     * 获取通知标题
     */
    @Override
    public String getNoticeTitle( Long id ) {
        if (ObjectKit.isEmpty(id)) {
            return "";
        }
        else {
            KtfNoticeVO notice = noticeDao.getByPk(id);
            if (notice == null) {
                return "";
            }
            else {
                return notice.getTitle();
            }
        }
    }

    /**
     * 获取字典名称
     */
    @Override
    public String getDictName( Long dictId ) {
        if (ObjectKit.isEmpty(dictId)) {
            return "";
        }
        else {

            String mkey = dictId.toString();
            if (dictMap.containsKey(mkey))
                return dictMap.get(mkey);

            DictVO dict = dictDao.getDict(dictId) ;
            if (dict == null) {
                return "";
            }
            else {
                dictMap.put(mkey, dict.getName());
                return dict.getName();
            }
        }
    }

    /**
     * 根据字典名称和字典中的值获取对应的名称
     */
    @Override
    public String getDictsByName( String name, String val ) {
        if (val == null)
            return "";

        String mkey = StrKit.join("_", name, val);
        if (dictMap.containsKey(mkey))
            return dictMap.get(mkey);

        
        List<DictVO> list =  dictDao.selectByName(name);
        if (list.isEmpty()) {
            return "";
        }
        else {
        	List<DictVO> dicts = dictDao.listSubDict(list.get(0).getId());

            for (DictVO item : dicts) {
                if (item.getValue() != null && item.getValue().equals(val)) {
                    dictMap.put(mkey, item.getName());
                    return item.getName();
                }
            }
            return "";
        }
    }

    @Override
    public void clearDictsCache() {
        dictMap.clear();
    }

    /**
     * 获取性别名称
     */
    @Override
    public String getSexName( String sex ) {
        return getDictsByName(WebConst.ATTR_SEX, sex);
    }

    /**
     * 获取用户登录状态
     */
    @Override
    public String getStatusName( Integer status ) {
        return KtfStatus.valueOf(status);
    }

    /**
     * 获取菜单状态
     */
    @Override
    public String getMenuStatusName( Integer status ) {
        return KtfStatus.valueOf(status);
    }

    /**
     * 查询字典
     */
    @Override
    public List<DictVO> findInDict( Long id ) {
        if (ObjectKit.isEmpty(id)) {
            return null;
        }
        else {
        	List<DictVO> dicts = dictDao.listSubDict(id);
            if (dicts == null || dicts.size() == 0) {
                return null;
            }
            else {
                return dicts;
            }
        }
    }

    /**
     * 获取被缓存的对象(用户删除业务)
     */
    @Override
    public String getCacheObject( String para ) {
        return null;
    }

    /**
     * 获取子部门id
     */
    @Override
    public List<Long> getSubDeptId( Long deptid ) {
        
        List<DeptVO> depts = deptDao.listSubDept(deptid);
        List<Long> deptids = depts.stream().map(DeptVO::getId).collect(Collectors.toList());

        return deptids;
    }

    /**
     * 获取所有父部门id
     */
    @Override
    public List<Long> getParentDeptIds( Long deptid ) {
    	DeptVO dept = deptDao.getDept(deptid);
        String pids = dept.getPids();
        String[] split = pids.split(",");
        ArrayList<Long> parentDeptIds = new ArrayList<>();
        for (String s : split) {
            parentDeptIds.add(Long.valueOf(StrKit.removeSuffix(StrKit.removePrefix(s, "["), "]")));
        }
        return parentDeptIds;
    }

    /*
     * @Override public Set<String> getPermissionsByUserId( Long userId ) {
     * return resourceDao.selectPermissions(userId); }
     */

}
