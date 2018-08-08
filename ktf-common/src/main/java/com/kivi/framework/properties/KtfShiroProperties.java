package com.kivi.framework.properties;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.kivi.framework.constant.enums.shiro.ShiroAuthType;
import com.kivi.framework.constant.enums.shiro.TokenAuthType;
import com.kivi.framework.util.kit.StrKit;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Configuration( KtfShiroProperties.BEAN_NAME )
@ConfigurationProperties( prefix = KtfShiroProperties.PREFIX )
@ConditionalOnProperty(
                        prefix = KtfShiroProperties.PREFIX,
                        name = "enabled",
                        havingValue = "true",
                        matchIfMissing = false )
public class KtfShiroProperties implements IKtfProperties {
    public static final String BEAN_NAME                 = "ktfShiroProperties";
    public static final String PREFIX                    = "ktf.shiro";

    private Boolean            enabled                   = true;
    private ShiroAuthType      type;
    private Long               sessionTimeout            = 3600000L;
    private Long               sessionValidationInterval = 900000L;
    private Long               rememberMeCookieTtl       = 604800L;
    private Token              token;
    private Filters            filters;
    private Forward            forward;

    @Getter
    @Setter
    public static class Token {
        private TokenAuthType authType = TokenAuthType.JWT;
        private Long          ttl      = 1800L;
        private Jwt           jwt;

        @Getter
        @Setter
        public static class Jwt {
            private String issuer     = "kivi";
            private String secretSeed = "kivi.jwt";
            private String signAlg    = "sha256";

            @Override
            public String toString() {
                return "Jwt [issuer=" + issuer + ", secretSeed=" + secretSeed + ", signAlg=" + signAlg + "]";
            }

        }
    }

    @Getter
    @Setter
    public static class Filters {
        private String anon   = "/index,/kaptcha,/**/login,/**/reg,/**/forget,/**/passReset,/favicon.ico,/api/auth";
        // 退出url过滤器
        private String logout = "/**/logout";
        // 需要管理员权限访问的url过滤器
        private String admin  = "/console/**";
        // 需要用户权限访问的url过滤器
        private String user   = "/member/**";
        // 需要token认证的访问url过滤器
        private String token  = "/api/**";

        public Map<String, String> getAnonMap( String value ) {
            return getFilterMap(value, anon);
        }

        public Map<String, String> getLogoutMap( String value ) {
            return getFilterMap(value, logout);
        }

        public Map<String, String> getAdminMap( String value ) {
            return getFilterMap(value, admin);
        }

        public Map<String, String> getUserMap( String value ) {
            return getFilterMap(value, user);
        }

        public Map<String, String> getTokenMap( String value ) {
            return getFilterMap(value, token);
        }

        private Map<String, String> getFilterMap( String value, String filter ) {
            Map<String, String> map = new LinkedHashMap<String, String>();

            String[] filters = StrKit.split(filter, ",");
            for (String url : filters) {
                if (StrKit.isNotEmpty(url))
                    map.put(url, value);
            }

            return map;
        }

        @Override
        public String toString() {
            return "Filters [anon=" + anon + ", logout=" + logout + ", admin=" + admin + ", user=" + user +
                    ", token=" + token + "]";
        }

    }

    @Getter
    @Setter
    public static class Forward {
        // 认证未通过重定向url
        private String unauthorized = "/html/403.html";
        // 管理员默认登陆url
        private String adminLogin   = "/console/login";
        // 管理员默认登录成功url
        private String adminSuccess = "/console/index";
        // 用户默认登陆url
        private String userLogin    = "/member/login";
        // 用户默认登录成功url
        private String userSuccess  = "/member/index";
    }

    @Override
    public String toString() {
        return "Shiro [enabled=" + enabled + ", type=" + type + ", sessionTimeout=" + sessionTimeout +
                ", sessionValidationInterval=" + sessionValidationInterval + ", rememberMeCookieTtl=" +
                rememberMeCookieTtl + ", token=" + token + ", filters=" + filters + ", forward=" + forward + "]";
    }

    @Override
    public String prefix() {
        return PREFIX;
    }

    @Override
    public String beanName() {
        return BEAN_NAME;
    }

}
