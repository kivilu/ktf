package com.kivi.framework.web.template.config;

import java.util.ArrayList;
import java.util.List;

/**
 * Service模板生成的配置
 *
 */
public class ServiceConfig {

    private ContextConfig contextConfig;

    private String        servicePathTemplate;
    private String        serviceImplPathTemplate;

    private String        packageName;

    private List<String>  serviceImplImports;

    public void init() {
        ArrayList<String> imports = new ArrayList<>();

        String basePackage = contextConfig.getBasePackage();

        imports.add("org.springframework.stereotype.Service");
        imports.add(basePackage + contextConfig.getModuleName() + ".service.I" +
                contextConfig.getBizEnBigName() + "Service");
        this.serviceImplImports = imports;
        this.packageName = basePackage + contextConfig.getModuleName() + ".service";

        basePackage = basePackage.replace(".", "\\");
        this.servicePathTemplate = "\\src\\main\\java\\" + basePackage +
                contextConfig.getModuleName() + "\\service\\I{}Service.java";
        this.serviceImplPathTemplate = "\\src\\main\\java\\" + basePackage +
                contextConfig.getModuleName() + "\\service\\impl\\{}ServiceImpl.java";

    }

    public String getServicePathTemplate() {
        return servicePathTemplate;
    }

    public void setServicePathTemplate( String servicePathTemplate ) {
        this.servicePathTemplate = servicePathTemplate;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName( String packageName ) {
        this.packageName = packageName;
    }

    public String getServiceImplPathTemplate() {
        return serviceImplPathTemplate;
    }

    public void setServiceImplPathTemplate( String serviceImplPathTemplate ) {
        this.serviceImplPathTemplate = serviceImplPathTemplate;
    }

    public List<String> getServiceImplImports() {
        return serviceImplImports;
    }

    public void setServiceImplImports( List<String> serviceImplImports ) {
        this.serviceImplImports = serviceImplImports;
    }

    public ContextConfig getContextConfig() {
        return contextConfig;
    }

    public void setContextConfig( ContextConfig contextConfig ) {
        this.contextConfig = contextConfig;
    }
}