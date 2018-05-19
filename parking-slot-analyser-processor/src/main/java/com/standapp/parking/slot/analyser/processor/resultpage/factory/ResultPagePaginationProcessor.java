package com.standapp.parking.slot.analyser.processor.resultpage.factory;

import com.standapp.parking.slot.analyser.fetcher.SoupFetcher;
import com.standapp.parking.slot.analyser.domain.queue.PageUrl;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultPagePaginationProcessor {

  @Autowired
  private ResultPageUrlFactory pageUrlFactory;

  @Autowired
  private SoupFetcher soupFetcher;

  public int getNumberOfPages() {
    Document soup = soupFetcher.fetch(pageUrlFactory.newResultPageUrl(1));

    return getNumberFromString(
        soup.select("div.pagination__page-number").first());
  }

  private int getNumberFromString(Element pageDiv) {
    return Integer.parseInt(pageDiv.html().split("/")[1].trim().split(" ")[0]);
  }
}
