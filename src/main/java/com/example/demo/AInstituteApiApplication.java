package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.asoft.ainstitute.api.repository"})
@EntityScan(basePackages = {"com.asoft.ainstitute.api.model"})
@EnableTransactionManagement
public class AInstituteApiApplication {
  private static final Logger logger = LoggerFactory.getLogger(AInstituteApiApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(AInstituteApiApplication.class, args);
  }

  @Bean
  CommandLineRunner init() {
    return (args) -> {
      logger.debug("Initialization - Start");
      logger.debug("Initialization - Complete");
    };
  }

}
