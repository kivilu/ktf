package com.kivi.framework.cache.constant;

public interface KtfCache {

    /**
     * ConstantFactory中的缓存
     */
    public final static String ROLES_NAME_CACHE           = "ktf.roles_name";

    public final static String SINGLE_ROLE_NAME_CACHE     = "ktf.single_role_name";

    public final static String SINGLE_ROLE_TIP_CACHE      = "ktf.single_role_tip";

    public final static String DEPT_NAME_CACHE            = "ktf.dept_name";

    // 用户登录次数计数 CACHE
    public final static String SHIRO_LOGIN_COUNT_CACHE    = "ktf.shiro_login_count_cache";

    // 用户登录是否被锁定 CACHE
    public final static String SHIRO_IS_LOCK_CACHE        = "ktf.shiro_is_lock";

    // 用户登录标识keyCACHE
    public final static String SHIRO_LOGIN_USER_CACHE     = "ktf.shiro_login_user";

    // 登录用户shiro用户对象信息
    public final static String SHIRO_PRINCIPAL_CACHE      = "ktf.shiro_principal";

    // 登录用户token对象信息缓存名称
    public final static String SHIRO_LOGIN_TOKEN_CACHE    = "ktf.shiro_login_token";

    // 密码重试缓存
    public final static String SHIRO_PASSWORD_RETRY_CACHE = "ktf.shiro_password_retry";

    public final static String SHIRO_KEY_PREFIX           = "ktf.shiro.session:";

    public final static String SYS_PARAM                  = "KTF_SYS_PARAM";

    // 文件上传缓存
    public final static String UPLOAD_CACHE               = "ktf.upload";

    public interface Dashboard {
        // 根据ID缓存
        public final static String USER_BY_ID      = "KTF.Admin.User.Id";

        // Account缓存
        public final static String USER_BY_ACCOUNT = "KTF.Admin.User.Account";

        // Debpt缓存
        public final static String DEPT            = "KTF.Admin.Dept";

        // Role缓存
        public final static String ROLE_VO         = "KTF.Admin.RoleVO";

    };
}
