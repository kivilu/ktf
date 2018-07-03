package com.kivi.framework.web.beetl;

import org.beetl.ext.spring.BeetlGroupUtilConfiguration;

import com.kivi.framework.util.ToolUtil;

public class BeetlConfiguration extends BeetlGroupUtilConfiguration {

    @Override
    public void initOther() {

        groupTemplate.registerFunctionPackage("shiro", new ShiroExt());
        groupTemplate.registerFunctionPackage("tool", new ToolUtil());

    }

}
