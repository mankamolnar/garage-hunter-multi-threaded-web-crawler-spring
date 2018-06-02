package com.standapp.parking.slot.analyser.runtime.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ThreadPool {

  private static final AtomicInteger CURRENTLY_RUNNING_THREADS = new AtomicInteger();

  @Value("${parking.slot.analyser.threads.cores}")
  private int numberOfThreads;

  @Value("${parking.slot.analyser.threads.max-cores}")
  private int maxNumberOfThreads;

  private final ThreadPoolTaskExecutor threadPoolTaskExecutor;
  private final JobExecutor jobExecutor;

  public void startThreads() {
    IntStream.range(0, maxNumberOfThreads)
        .forEach(index -> {
          threadPoolTaskExecutor.execute(new Thread(() -> {
            log.info("Thread is starting");

            jobExecutor.execute();

            log.info("Closing thread!");
          }));
    });
  }

  public static void incrementAndGet() {
    CURRENTLY_RUNNING_THREADS.incrementAndGet();
  }

  public static int decrementAndGet() {
    return CURRENTLY_RUNNING_THREADS.decrementAndGet();
  }
}
