package com.standapp.parking.slot.analyser.processor.garagedetails.mapper;

import com.standapp.parking.slot.analyser.domain.queue.Garage;
import com.standapp.parking.slot.analyser.domain.queue.PageUrl;
import java.util.Optional;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class GarageMapper {

  public Garage createGarageFromElements(
      PageUrl pageUrl, Element title, Element area, Element price, Element img) {
    return Garage.builder()
        .id(getIdFromUrl(pageUrl))
        .district(getDistrictFromTitle(title))
        .street(getStreetFromTitle(title))
        .area(getAreaFromElement(area))
        .price(getPriceFromElement(price))
        .img(getImgFromElement(img))
        .build();
  }

  private String getIdFromUrl(PageUrl pageUrl) {
    return pageUrl.getPageUrl().split("/")[7];
  }

  private String getDistrictFromTitle(Element title) {
    return title.html().split("\\.")[0].trim();
  }

  private String getStreetFromTitle(Element title) {
    return title.html().split(",")[1].trim();
  }

  private Optional<String> getAreaFromElement(Element area) {
    if (area != null) {
      return Optional.of(area.html().split(" ")[0].trim());
    }
    return Optional.empty();
  }

  private Optional<String> getPriceFromElement(Element price) {
    if (price != null) {
      return Optional.of(price.html().trim());
    }
    return Optional.empty();
  }

  private Optional<String> getImgFromElement(Element img) {
    if (img != null) {
      return Optional.of(img.attr("style").split("'")[1]);
    }
    return Optional.empty();
  }
}
