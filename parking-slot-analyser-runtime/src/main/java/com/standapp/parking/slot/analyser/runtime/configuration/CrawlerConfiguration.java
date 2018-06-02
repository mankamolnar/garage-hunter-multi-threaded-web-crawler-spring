package com.standapp.parking.slot.analyser.runtime.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@Slf4j
public class CrawlerConfiguration {

  @Value("${parking.slot.analyser.threads.cores}")
  private Integer cores;

  @Value("${parking.slot.analyser.threads.max-cores}")
  private Integer maxCores;

  @Bean
  public ThreadPoolTaskExecutor taskExecutor() {
    log.info("THREAD POOL EXECUTOR MAX CORES: " + cores + " " + maxCores);

    ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
    pool.setCorePoolSize(cores);
    pool.setMaxPoolSize(maxCores);
    pool.setWaitForTasksToCompleteOnShutdown(true);
    pool.initialize();

    return pool;
  }
}
