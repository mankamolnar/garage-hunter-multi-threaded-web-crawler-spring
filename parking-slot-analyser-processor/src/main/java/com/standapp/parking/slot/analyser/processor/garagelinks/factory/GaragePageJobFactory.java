package com.standapp.parking.slot.analyser.processor.garagelinks.factory;

import org.jsoup.nodes.Element;
import com.standapp.parking.slot.analyser.domain.queue.Job;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GaragePageJobFactory {

  private final GarageDetailsPageFactory garageDetailsPageFactory;

  public Job newGaragePageJobFactory(Element link) {
    return Job.builder()
        .job(garageDetailsPageFactory.newGarageDetailsPage(link.attr("href")))
        .build();
  }
}
