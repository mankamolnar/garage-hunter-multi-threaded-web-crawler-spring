package com.standapp.parking.slot.analyser.domain.queue;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PageUrl implements Consumable {

  private final String pageUrl;
  private final PageType type;

  @Override
  public boolean equals(Object object) {
    if (object == null) {
      return false;
    }
    if (object instanceof PageUrl) {
      return this.pageUrl.equals(((PageUrl) object).getPageUrl());
    } else {
      return false;
    }
  }
}
