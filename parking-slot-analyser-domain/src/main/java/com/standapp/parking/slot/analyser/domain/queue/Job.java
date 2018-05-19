package com.standapp.parking.slot.analyser.domain.queue;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Job {

  private Consumable job;
}
