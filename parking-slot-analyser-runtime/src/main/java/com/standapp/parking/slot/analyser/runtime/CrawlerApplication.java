package com.standapp.parking.slot.analyser.runtime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.standapp.parking.slot.analyser")
@EnableJpaRepositories("com.standapp.parking.slot.analyser.persistence")
@EntityScan("com.standapp.parking.slot.analyser.persistence")
public class CrawlerApplication {

  public static void main(String[] args) {
    SpringApplication.run(CrawlerApplication.class, args);
  }
}
