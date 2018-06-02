package com.standapp.parking.slot.analyser.runtime.scheduler;

import com.standapp.parking.slot.analyser.persistence.service.ModificationTimeProvider;
import com.standapp.parking.slot.analyser.processor.resultpage.ResultPageQueueProducer;
import com.standapp.parking.slot.analyser.runtime.thread.ThreadPool;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Scheduler {

  private final ResultPageQueueProducer resultPageQueueProducer;
  private final ThreadPool threadPool;
  private final ModificationTimeProvider modificationTimeProvider;

  @Scheduled(cron = "0 0/30 9,19 * * *")
  public void start() {
    modificationTimeProvider.setModificationTimeToPresentMoment();
    resultPageQueueProducer.produceResultPageUrls();
    threadPool.startThreads();
  }
}
