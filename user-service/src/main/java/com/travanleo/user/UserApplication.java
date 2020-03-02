package com.travanleo.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;


@SpringBootApplication
@ComponentScan(basePackages = {"com.travanleo.user"})
@EntityScan(basePackages = {"com.travanleo.user.domain", "org.springframework.data.jpa.convert.threeten"})
@EnableJpaRepositories(basePackages = "com.travanleo.user.repo")
@EnableSpringDataWebSupport
public class UserApplication {

  public static void main(String[] args) {
    SpringApplication.run(UserApplication.class, args);
  }

}
