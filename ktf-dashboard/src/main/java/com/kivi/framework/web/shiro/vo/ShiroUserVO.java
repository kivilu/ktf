package com.kivi.framework.web.shiro.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShiroUserVO implements Serializable {
    /**
     * 
     */
    private static final long  serialVersionUID       = 1L;

    public static final String SHIRO_USER_SESSION_KEY = "shiro_user_session_key";

    public Long                id;                                               // 主键ID
    public String              account;                                          // 账号
    public String              name;                                             // 姓名
    public Long                deptId;                                           // 部门id
    public List<Long>          roleList;                                         // 角色集
    public String              deptName;                                         // 部门名称
    public List<String>        roleNames;                                        // 角色名称集

    private boolean            isLogin;

    public boolean isLogin() {
        return this.isLogin;
    }

    public boolean getIsLogin() {

        return isLogin();
    }

}
