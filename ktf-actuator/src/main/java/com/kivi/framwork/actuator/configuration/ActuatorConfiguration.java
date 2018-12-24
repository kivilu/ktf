package com.kivi.framwork.actuator.configuration;

// @Configuration
// @ConditionalOnProperty(
// prefix = "security.basic",
// name = "enabled",
// havingValue = "true",
// matchIfMissing = false )
// @EnableWebSecurity
/*
 * public class ActuatorConfiguration extends WebSecurityConfigurerAdapter {
 * 
 * @Autowired Environment env;
 * 
 * @Override protected void configure( HttpSecurity http ) throws Exception {
 * 
 * 
 * String contextPath = env.getProperty("management.context-path"); if
 * (StringUtils.isEmpty(contextPath)) { contextPath = ""; }
 * http.csrf().disable(); http.authorizeRequests().antMatchers("/**" +
 * contextPath + "/**").authenticated().anyRequest().permitAll()
 * .and().httpBasic();
 * 
 * }
 * 
 * 
 * @Bean public ActuatorCustomizer getActuatorCustomizer() { ActuatorCustomizer
 * actuator = new ActuatorCustomizer(); return actuator; }
 * 
 * 
 * }
 */
