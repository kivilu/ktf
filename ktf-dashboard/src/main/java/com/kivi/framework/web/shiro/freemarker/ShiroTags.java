package com.kivi.framework.web.shiro.freemarker;

import java.util.Map;

import freemarker.template.ObjectWrapper;
import freemarker.template.SimpleHash;

/**
 * Shortcut for injecting the tags into Freemarker
 *
 * <p>
 * Usage: cfg.setSharedVeriable("shiro", new ShiroTags());
 * </p>
 */
public class ShiroTags extends SimpleHash {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ShiroTags( Map arg0, ObjectWrapper arg1 ) {
        super(arg0, arg1);
    }

    public ShiroTags( ObjectWrapper wrapper ) {
        super(wrapper);
    }

    public ShiroTags() {
        put("authenticated", new AuthenticatedTag());
        put("guest", new GuestTag());
        put("hasAnyRoles", new HasAnyRolesTag());
        put("hasPermission", new HasPermissionTag());
        put("hasRole", new HasRoleTag());
        put("lacksPermission", new LacksPermissionTag());
        put("lacksRole", new LacksRoleTag());
        put("notAuthenticated", new NotAuthenticatedTag());
        put("principal", new PrincipalTag());
        put("user", new UserTag());
    }
}
