package com.standapp.parking.slot.analyser.processor.garagelinks.factory;

import com.standapp.parking.slot.analyser.domain.queue.PageType;
import com.standapp.parking.slot.analyser.domain.queue.PageUrl;
import org.springframework.stereotype.Service;

@Service
public class GarageDetailsPageFactory {

  private static final String BASE_URL = "https://ingatlan.com";

  public PageUrl newGarageDetailsPage(String url) {
    return new PageUrl(
        BASE_URL + url,
        PageType.GARAGE_DETAILS);
  }
}
