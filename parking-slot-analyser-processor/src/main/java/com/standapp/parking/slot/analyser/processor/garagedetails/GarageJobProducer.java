package com.standapp.parking.slot.analyser.processor.garagedetails;

import com.standapp.parking.slot.analyser.container.queue.JobQueue;
import com.standapp.parking.slot.analyser.domain.queue.Garage;
import com.standapp.parking.slot.analyser.domain.queue.Job;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GarageJobProducer {

  private final JobQueue jobQueue;

  public void produceGarageJob(Garage garage) {
    jobQueue.add(Job.builder()
        .job(garage)
        .build());
  }
}
