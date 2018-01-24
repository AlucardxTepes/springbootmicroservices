package com.alucard.commentstore;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**") // endpoints to affect
        .allowedOrigins("http://localhost:4200")
        .allowedMethods("GET", "DELETE")
        .allowedHeaders("*") // All headers allowed
        .allowCredentials(true) // time in seconds for browser to cache this info
        .maxAge(3600);
  }
}
