package com.kivi.framwork.actuator.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.kivi.framwork.actuator.mapping.ActuatorCustomizer;

@Configuration
@EnableWebSecurity
public class ActuatorConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    Environment env;

    @Override
    protected void configure( HttpSecurity http ) throws Exception {
        /*
         * String contextPath = env.getProperty("management.context-path"); if
         * (StringUtils.isEmpty(contextPath)) { contextPath = ""; }
         * http.csrf().disable(); http.authorizeRequests() .antMatchers("/**" +
         * contextPath + "/**").authenticated() .anyRequest().permitAll()
         * .and().httpBasic();
         */
    }

    @Bean
    public ActuatorCustomizer getActuatorCustomizer() {
        ActuatorCustomizer actuator = new ActuatorCustomizer();
        return actuator;
    }

}
