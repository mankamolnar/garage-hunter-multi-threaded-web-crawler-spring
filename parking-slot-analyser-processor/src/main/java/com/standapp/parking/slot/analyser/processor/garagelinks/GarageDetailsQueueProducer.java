package com.standapp.parking.slot.analyser.processor.garagelinks;

import com.standapp.parking.slot.analyser.container.queue.JobQueue;
import com.standapp.parking.slot.analyser.domain.queue.Job;
import com.standapp.parking.slot.analyser.domain.queue.PageUrl;
import com.standapp.parking.slot.analyser.processor.garagelinks.factory.GarageDetailsPageFactory;
import com.standapp.parking.slot.analyser.processor.garagelinks.factory.GaragePageJobFactory;
import com.standapp.parking.slot.analyser.processor.garagelinks.factory.ResultsProcessor;
import lombok.RequiredArgsConstructor;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GarageDetailsQueueProducer {

  private final ResultsProcessor resultsProcessor;
  private final GaragePageJobFactory garageDetailsPageFactory;
  private final JobQueue jobQueue;

  public void produceGarageDetailsPages(PageUrl resultsPage) {
    Elements links = resultsProcessor.getLinksOfPage(resultsPage);

    links.forEach(link -> jobQueue.add(garageDetailsPageFactory.newGaragePageJobFactory(link)));
  }

}
