package com.standapp.parking.slot.analyser.runtime.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ThreadPool {

  private static final int MAX_NUMBER_OF_THREADS = 5;
  private static final AtomicInteger CURRENTLY_RUNNING_THREADS = new AtomicInteger();

  private final ThreadPoolTaskExecutor threadPoolTaskExecutor;
  private final ThreadBody threadBody;

  public void startThreads() {
    IntStream.range(0, MAX_NUMBER_OF_THREADS)
        .forEach(index -> {
          threadPoolTaskExecutor.execute(new Thread(() -> {
            log.info("Thread is starting");

            threadBody.run();

            log.info("Closing thread!");
          }));
    });
  }

  public static int incrementAndGet() {
    return CURRENTLY_RUNNING_THREADS.incrementAndGet();
  }

  public static int decrementAndGet() {
    return CURRENTLY_RUNNING_THREADS.decrementAndGet();
  }
}
