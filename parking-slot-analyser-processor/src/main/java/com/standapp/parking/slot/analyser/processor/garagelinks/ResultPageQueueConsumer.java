package com.standapp.parking.slot.analyser.processor.garagelinks;

import com.standapp.parking.slot.analyser.domain.queue.PageUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultPageQueueConsumer {

  @Autowired
  private GarageDetailsQueueProducer garageDetailsQueueProducer;

  public void consumeResultPages(PageUrl pageUrl) {
    garageDetailsQueueProducer.produceGarageDetailsPages(pageUrl);
  }
}
