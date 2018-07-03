package com.kivi.framework.web.template.engine;

import com.kivi.framework.util.kit.StrKit;
import com.kivi.framework.web.template.engine.base.KtfWebTemplateEngine;

/**
 * 通用的模板生成引擎
 *
 */
public class SimpleTemplateEngine extends KtfWebTemplateEngine {

    @Override
    protected void generatePageEditHtml() {
        String path = StrKit.format(
                super.getContextConfig().getProjectPath() + getPageConfig().getPageEditPathTemplate(),
                super.getContextConfig().getBizEnName(), super.getContextConfig().getBizEnName());
        generateFile("ktfTemplate/page_edit.html.btl", path);
        System.out.println("生成编辑页面成功!");
    }

    @Override
    protected void generatePageAddHtml() {
        String path = StrKit.format(
                super.getContextConfig().getProjectPath() + getPageConfig().getPageAddPathTemplate(),
                super.getContextConfig().getBizEnName(), super.getContextConfig().getBizEnName());
        generateFile("ktfTemplate/page_add.html.btl", path);
        System.out.println("生成添加页面成功!");
    }

    @Override
    protected void generatePageInfoJs() {
        String path = StrKit.format(
                super.getContextConfig().getProjectPath() + getPageConfig().getPageInfoJsPathTemplate(),
                super.getContextConfig().getBizEnName(), super.getContextConfig().getBizEnName());
        generateFile("ktfTemplate/page_info.js.btl", path);
        System.out.println("生成页面详情js成功!");
    }

    @Override
    protected void generatePageJs() {
        String path = StrKit.format(
                super.getContextConfig().getProjectPath() + getPageConfig().getPageJsPathTemplate(),
                super.getContextConfig().getBizEnName(), super.getContextConfig().getBizEnName());
        generateFile("ktfTemplate/page.js.btl", path);
        System.out.println("生成页面js成功!");
    }

    @Override
    protected void generatePageHtml() {
        String path = StrKit.format(super.getContextConfig().getProjectPath() + getPageConfig().getPagePathTemplate(),
                super.getContextConfig().getBizEnName(), super.getContextConfig().getBizEnName());
        generateFile("ktfTemplate/page.html.btl", path);
        System.out.println("生成页面成功!");
    }

    @Override
    protected void generateController() {
        String controllerPath = StrKit.format(
                super.getContextConfig().getProjectPath() + super.getControllerConfig().getControllerPathTemplate(),
                StrKit.firstCharToUpperCase(super.getContextConfig().getBizEnName()));
        generateFile("ktfTemplate/Controller.java.btl", controllerPath);
        System.out.println("生成控制器成功!");
    }

    @Override
    protected void generateDao() {
        String daoPath = StrKit.format(
                super.getContextConfig().getProjectPath() + super.getDaoConfig().getDaoPathTemplate(),
                StrKit.firstCharToUpperCase(super.getContextConfig().getBizEnName()));
        generateFile("ktfTemplate/Dao.java.btl", daoPath);
        System.out.println("生成Dao成功!");

        String mappingPath = StrKit.format(
                super.getContextConfig().getProjectPath() + super.getDaoConfig().getXmlPathTemplate(),
                StrKit.firstCharToUpperCase(super.getContextConfig().getBizEnName()));
        generateFile("ktfTemplate/Mapping.xml.btl", mappingPath);
        System.out.println("生成Dao Mapping xml成功!");
    }

    @Override
    protected void generateService() {
        String servicePath = StrKit.format(
                super.getContextConfig().getProjectPath() + super.getServiceConfig().getServicePathTemplate(),
                StrKit.firstCharToUpperCase(super.getContextConfig().getBizEnName()));
        generateFile("ktfTemplate/Service.java.btl", servicePath);
        System.out.println("生成Service成功!");

        String serviceImplPath = StrKit.format(
                super.getContextConfig().getProjectPath() + super.getServiceConfig().getServiceImplPathTemplate(),
                StrKit.firstCharToUpperCase(super.getContextConfig().getBizEnName()));
        generateFile("ktfTemplate/ServiceImpl.java.btl", serviceImplPath);
        System.out.println("生成ServiceImpl成功!");
    }
}
