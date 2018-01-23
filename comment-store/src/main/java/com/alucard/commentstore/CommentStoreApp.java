package com.alucard.commentstore;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.codeboje.springbootbook.commons.CommentstoreObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ImportResource(value = {"classpath*:legacy-context.xml"})
@RestController
@SpringBootApplication
@EnableTransactionManagement
public class CommentStoreApp {

  @Bean
  @Primary
  public ObjectMapper initObjectMapper() {
    return new CommentstoreObjectMapper();
  }

  public static void main(String[] args) {
    SpringApplication.run(CommentStoreApp.class, args);
  }

  @RequestMapping("/")
  String home() {
    return "Klk dice";
  }

}
