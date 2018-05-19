package com.standapp.parking.slot.analyser.processor.resultpage;

import com.standapp.parking.slot.analyser.container.queue.JobQueue;
import com.standapp.parking.slot.analyser.processor.resultpage.factory.ResultPageJobFactory;
import com.standapp.parking.slot.analyser.processor.resultpage.factory.ResultPagePaginationProcessor;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultPageQueueProducer {

  @Autowired
  private ResultPageJobFactory resultPageJobFactory;

  @Autowired
  private ResultPagePaginationProcessor pageProcessor;

  @Autowired
  private JobQueue jobQueue;

  public void produceResultPageUrls() {
    int numberOfPages = pageProcessor.getNumberOfPages();
    IntStream.range(1, numberOfPages + 1)
        .forEach(index -> jobQueue.add(resultPageJobFactory.newJob(index)));
  }
}
