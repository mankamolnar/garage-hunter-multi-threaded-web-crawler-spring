package com.standapp.parking.slot.analyser.persistence.service;

import java.util.Date;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
public class ModificationTimeProvider {

  private Date modificationTime;

  public void setModificationTimeToPresentMoment() {
    modificationTime = new Date();
  }
}
