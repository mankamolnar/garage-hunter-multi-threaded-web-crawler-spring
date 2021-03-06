package com.standapp.parking.slot.analyser.persistence;

import com.standapp.parking.slot.analyser.persistence.domain.DbGarage;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GarageRepository extends CrudRepository<DbGarage, Long> {
  Optional<DbGarage> findDistinctByExternalId(Long id);
  List<DbGarage> findAllByExternalId(Long id);
}
