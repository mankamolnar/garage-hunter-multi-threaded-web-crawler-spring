package com.standapp.parking.slot.analyser.runtime.controller;

import com.standapp.parking.slot.analyser.persistence.service.ModificationTimeProvider;
import com.standapp.parking.slot.analyser.processor.resultpage.ResultPageQueueProducer;
import com.standapp.parking.slot.analyser.runtime.thread.ThreadPool;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/with-threads")
@RequiredArgsConstructor
public class ThreadController {

  private final ResultPageQueueProducer resultPageQueueProducer;
  private final ThreadPool threadPool;
  private final ModificationTimeProvider modificationTimeProvider;

  @RequestMapping(method = RequestMethod.GET)
  public String greeting() {
    modificationTimeProvider.setModificationTimeToPresentMoment();
    resultPageQueueProducer.produceResultPageUrls();
    threadPool.startThreads();

    return "gomba";
  }
}
