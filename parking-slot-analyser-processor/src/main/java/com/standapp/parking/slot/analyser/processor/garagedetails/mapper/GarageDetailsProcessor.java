package com.standapp.parking.slot.analyser.processor.garagedetails.mapper;

import com.standapp.parking.slot.analyser.fetcher.SoupFetcher;
import com.standapp.parking.slot.analyser.domain.queue.Garage;
import com.standapp.parking.slot.analyser.domain.queue.PageUrl;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GarageDetailsProcessor {

  @Autowired
  private GarageMapper mapper;

  @Autowired
  private SoupFetcher soupFetcher;

  public Garage processPage(PageUrl pageUrl) {
    Document soup = soupFetcher.fetch(pageUrl);
    Element title = soup.select("h1.js-listing-title").first();
    Element area = soup.select(".parameter-area-size .parameter-value").first();
    Element price = soup.select(".parameter-price .parameter-value").first();
    Element img = soup.select(".image-holder .image").first();

    return mapper.createGarageFromElements(pageUrl, title, area, price, img);
  }

}
