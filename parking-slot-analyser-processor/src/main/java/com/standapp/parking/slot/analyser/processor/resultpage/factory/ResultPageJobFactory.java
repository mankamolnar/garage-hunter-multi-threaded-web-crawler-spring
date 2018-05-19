package com.standapp.parking.slot.analyser.processor.resultpage.factory;

import com.standapp.parking.slot.analyser.domain.queue.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultPageJobFactory {

  @Autowired
  private ResultPageUrlFactory resultPageUrlFactory;

  public Job newJob(int index) {
    return Job.builder()
        .job(resultPageUrlFactory.newResultPageUrl(index))
        .build();
  }
}
