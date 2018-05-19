package com.standapp.parking.slot.analyser.persistence.domain;

import com.standapp.parking.slot.analyser.persistence.service.ModificationTimeProvider;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RentedOutGarageSigner {

  private final EntityManagerFactory entityManagerFactory;
  private final ModificationTimeProvider modificationTimeProvider;

  public void signRentedOutGarages() {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    Session session = (Session) entityManager.getDelegate();
    Query query = session.createQuery(
        "update DbGarage set endDate = :lastModified1 where lastModified <> :lastModified2 and endDate is null");
    Transaction transaction = session.beginTransaction();

    query.setParameter("lastModified1", modificationTimeProvider.getModificationTime());
    query.setParameter("lastModified2", modificationTimeProvider.getModificationTime());
    query.executeUpdate();
    transaction.commit();
  }
}
