package com.standapp.parking.slot.analyser.fetcher;

import com.standapp.parking.slot.analyser.domain.connection.JsoupConnectionException;
import com.standapp.parking.slot.analyser.domain.queue.PageUrl;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public class SoupFetcher {

  public Document fetch(PageUrl page) {
    try {
      return Jsoup.connect(page.getPageUrl()).get();
    } catch (IOException e) {
      e.printStackTrace();
      throw new JsoupConnectionException("Could not connect to the given URL", e);
    }
  }
}
