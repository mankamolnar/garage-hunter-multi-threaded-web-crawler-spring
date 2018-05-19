package com.standapp.parking.slot.analyser.processor.garagelinks.factory;

import com.standapp.parking.slot.analyser.fetcher.SoupFetcher;
import com.standapp.parking.slot.analyser.domain.queue.PageUrl;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultsProcessor {

  @Autowired
  private SoupFetcher soupFetcher;

  public Elements getLinksOfPage(PageUrl pageUrl) {
    Document soup = soupFetcher.fetch(pageUrl);

    return soup.select("a.listing__link");
  }
}
