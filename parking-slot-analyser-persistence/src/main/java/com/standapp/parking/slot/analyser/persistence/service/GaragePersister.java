package com.standapp.parking.slot.analyser.persistence.service;

import com.standapp.parking.slot.analyser.domain.queue.Garage;
import com.standapp.parking.slot.analyser.persistence.GarageRepository;
import com.standapp.parking.slot.analyser.persistence.domain.DbGarage;
import com.standapp.parking.slot.analyser.persistence.service.factory.DbGarageFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GaragePersister {

  private final GarageRepository garageRepository;
  private final DbGarageFactory dbGarageFactory;
  private final ModificationTimeProvider modificationTimeProvider;

  public void persist(Garage garage) {
    log.info("Persist garage: " + garage.toString());

    List<DbGarage> garages = garageRepository.findAllByExternalId(
        Long.parseLong(garage.getId()));

    if (garages.size() == 0) {
      garageRepository.save(dbGarageFactory.newDbGarage(garage));
    } else {
      DbGarage dbGarage = garages.get(0);
      dbGarage.setLastModified(modificationTimeProvider.getModificationTime());

      garageRepository.save(dbGarage);
    }
  }
}
