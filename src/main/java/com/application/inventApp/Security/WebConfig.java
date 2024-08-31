package com.application.inventApp.Security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/* dddd */
@Configuration
@EnableWebMvc
public class WebConfig implements  WebMvcConfigurer{

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("**")
        .allowedOrigins("http://localhost:4200")
        .allowedMethods("POST", "GET")
        .allowedHeaders("header1")
        .exposedHeaders("header1")
        .allowCredentials(true);
  }
}

