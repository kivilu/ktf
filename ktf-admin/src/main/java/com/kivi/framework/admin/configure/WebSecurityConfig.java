package com.kivi.framework.admin.configure;

// import
// org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import
// org.springframework.security.config.annotation.web.builders.WebSecurity;
// import
// org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// @Configuration
// @EnableWebSecurity
// @EnableGlobalMethodSecurity( securedEnabled = true, prePostEnabled = true,
// proxyTargetClass = true )
// public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
// @Override
// public void configure( WebSecurity web ) throws Exception {
// // 忽略css.jq.img等文件
// web.ignoring().antMatchers("/**.html", "/**.css", "/img/**", "/**.js",
// "/third-party/**");
// }

// protected void configure111( HttpSecurity http ) throws Exception {
// http
// .csrf().disable() // HTTP with Disable CSRF
// .authorizeRequests() // Authorize Request Configuration
// .antMatchers("/login",
// "/info",
// "/health",
// "/api/**",
// "/**/heapdump",
// "/**/loggers",
// "/**/liquibase",
// "/**/logfile",
// "/**/flyway",
// "/**/auditevents",
// "/**/jolokia",
// "/**/threads",
// "/**/trace",
// "/**/refresh",
// "/**/dump")
// .permitAll()
// .and()
// .authorizeRequests()
// .antMatchers("/**").hasRole("USER")
// .antMatchers("/**").hasRole("SUPERUSER")
// .antMatchers("/**/**").authenticated()
// .and() // Login Form configuration for all others
// .formLogin()
// .loginPage("/login.html")
// .loginProcessingUrl("/login").permitAll()
// .defaultSuccessUrl("/")
// .and() // Logout Form configuration
// .logout()
// .deleteCookies("remove")
// .logoutSuccessUrl("/login.html").permitAll()
// .and()
// .httpBasic();
// }

// }
