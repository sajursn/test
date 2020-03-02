package com.travanleo.user.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Setting up ignore points and disabling basic authentication.
 * 
 *
 */
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Value("${ignore.security.endpoints}")
  String ignoredEndpoints;

  @Value("${cors.allowedHosts.urls:http://localhost:3000}")
  String allowedHosts;

  @Override
  public void configure(WebSecurity web) {
    web.ignoring().mvcMatchers(ignoredEndpoints.split(";")).antMatchers(HttpMethod.OPTIONS);
    ;

  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().httpBasic().disable();
  }

  @Bean(name = "corsFilter ")
  public CorsFilter corsFilter() {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    final CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    // config.applyPermitDefaultValues();
    // config.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
    config.addAllowedHeader("*");

    for (String allowedHost : allowedHosts.split(";")) {
      config.addAllowedOrigin(allowedHost);
    }

    config.addAllowedMethod("OPTIONS");
    config.addAllowedMethod("HEAD");
    config.addAllowedMethod("GET");
    config.addAllowedMethod("PUT");
    config.addAllowedMethod("POST");
    config.addAllowedMethod("DELETE");
    config.addAllowedMethod("PATCH");

    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
  }

}
