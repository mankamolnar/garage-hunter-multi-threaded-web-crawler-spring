package com.standapp.parking.slot.analyser.processor.garagedetails;

import com.standapp.parking.slot.analyser.domain.queue.Garage;
import com.standapp.parking.slot.analyser.domain.queue.PageUrl;
import com.standapp.parking.slot.analyser.processor.garagedetails.mapper.GarageDetailsProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GarageDetailsQueueConsumer {

  private final GarageDetailsProcessor garageDetailsProcessor;
  private final GarageJobProducer garageJobProducer;

  public void consumeGarageDetails(PageUrl pageUrl) {
    Garage garage = garageDetailsProcessor.processPage(pageUrl);

    garageJobProducer.produceGarageJob(garage);
  }
}
