package com.standapp.parking.slot.analyser.runtime.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ApplicationConfiguration {

  @Bean
  public ThreadPoolTaskExecutor taskExecutor() {
    ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
    pool.setCorePoolSize(5);
    pool.setMaxPoolSize(10);
    pool.setWaitForTasksToCompleteOnShutdown(true);
    pool.initialize();

    return pool;
  }
}
