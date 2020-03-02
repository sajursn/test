package com.travanleo.springsecurity.oauthserver.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Basic Web configurations
 * 
 *
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @Autowired
  @Qualifier("userDetailsService")
  UserDetailsService userdetailsService;

  @Value("${ignore.security.endpoints}")
  String ignoredEndpoints;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userdetailsService).passwordEncoder(passwordEncoder);
  }

  @Override
  public void configure(WebSecurity web) {
    web.ignoring().mvcMatchers(ignoredEndpoints.split(";"));

  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  /*
   * 
   * @Autowired
   * public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {
   * // @formatter:off
   * auth.inMemoryAuthentication()
   * .withUser("john").password(passwordEncoder.encode("secret")).roles("USER").and()
   * .withUser("tom").password(passwordEncoder.encode("secret")).roles("ADMIN");
   * 
   * 
   * System.out.println("Encoding for password"+passwordEncoder.encode("secret"));
   * }// @formatter:on
   */

}
