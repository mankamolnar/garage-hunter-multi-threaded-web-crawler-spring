package com.standapp.parking.slot.analyser.persistence.service.factory;

import com.standapp.parking.slot.analyser.domain.queue.Garage;
import com.standapp.parking.slot.analyser.persistence.domain.DbGarage;
import com.standapp.parking.slot.analyser.persistence.service.ModificationTimeProvider;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DbGarageFactory {

  private final ModificationTimeProvider modificationTimeProvider;

  public DbGarage newDbGarage(Garage garage) {
    DbGarage dbGarage = new DbGarage();

    dbGarage.setExternalId(Long.parseLong(garage.getId()));
    dbGarage.setStartDate(new Date());
    dbGarage.setLastModified(modificationTimeProvider.getModificationTime());
    dbGarage.setDistrict(garage.getDistrict());
    dbGarage.setStreet(garage.getStreet());
    dbGarage.setArea(garage.getArea().orElse(""));
    dbGarage.setPrice(garage.getPrice().orElse(""));
    dbGarage.setImg(garage.getImg().orElse(""));

    return dbGarage;
  }
}
