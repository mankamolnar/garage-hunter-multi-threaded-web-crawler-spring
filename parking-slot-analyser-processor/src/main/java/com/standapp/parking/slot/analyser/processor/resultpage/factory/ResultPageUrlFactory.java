package com.standapp.parking.slot.analyser.processor.resultpage.factory;

import com.standapp.parking.slot.analyser.domain.queue.PageType;
import com.standapp.parking.slot.analyser.domain.queue.PageUrl;
import org.springframework.stereotype.Service;

@Service
public class ResultPageUrlFactory {

  private static String BASE_URL = "https://ingatlan.com/lista/kiado+garazs+budapest";

  public PageUrl newResultPageUrl(Integer page) {
    return new PageUrl(
        BASE_URL + "?page=" + page,
        PageType.GARAGE_SEARCH_RESULTS);
  }
}
