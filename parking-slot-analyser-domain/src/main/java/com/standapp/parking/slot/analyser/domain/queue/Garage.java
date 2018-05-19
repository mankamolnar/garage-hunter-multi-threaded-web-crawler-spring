package com.standapp.parking.slot.analyser.domain.queue;

import java.util.Optional;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Garage implements Consumable {

  private final String id;
  private final String district;
  private final String street;
  private final Optional<String> area;
  private final Optional<String> price;
  private final Optional<String> img;
}
