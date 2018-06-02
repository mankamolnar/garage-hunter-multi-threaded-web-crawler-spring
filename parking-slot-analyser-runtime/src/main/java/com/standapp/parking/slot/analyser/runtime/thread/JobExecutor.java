package com.standapp.parking.slot.analyser.runtime.thread;

import com.standapp.parking.slot.analyser.container.queue.JobQueue;
import com.standapp.parking.slot.analyser.domain.queue.Garage;
import com.standapp.parking.slot.analyser.domain.queue.Job;
import com.standapp.parking.slot.analyser.domain.queue.PageType;
import com.standapp.parking.slot.analyser.domain.queue.PageUrl;
import com.standapp.parking.slot.analyser.persistence.domain.RentedOutGarageSigner;
import com.standapp.parking.slot.analyser.persistence.service.GaragePersister;
import com.standapp.parking.slot.analyser.processor.garagedetails.GarageDetailsQueueConsumer;
import com.standapp.parking.slot.analyser.processor.garagelinks.ResultPageQueueConsumer;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JobExecutor {

  private final JobQueue jobQueue;
  private final ResultPageQueueConsumer resultPageQueueConsumer;
  private final GarageDetailsQueueConsumer garageDetailsQueueConsumer;
  private final GaragePersister garagePersister;
  private final RentedOutGarageSigner rentedOutGarageSigner;

  public void execute() {
    ThreadPool.incrementAndGet();

    consumeJobQueue();
    signRentedOutGarages();
  }

  public void consumeJobQueue() {
    while (!jobQueue.isEmpty()) {
      Optional<Job> optionalJob = jobQueue.removeFirst();

      optionalJob.ifPresent(this::doJob);
    }
  }

  public void signRentedOutGarages() {
    int currentlyRunningThreads = ThreadPool.decrementAndGet();

    if (currentlyRunningThreads == 0) {
      rentedOutGarageSigner.signRentedOutGarages();
    }
  }

  private void doJob(Job job) {
    if (job.getJob() instanceof PageUrl) {
      doPageUrlJob(job);
    } else if (job.getJob() instanceof Garage) {
      doGarageJob(job);
    }
  }

  private void doPageUrlJob(Job job) {
    PageUrl pageUrl = (PageUrl)job.getJob();

    if (pageUrl.getType().equals(PageType.GARAGE_SEARCH_RESULTS)) {
      resultPageQueueConsumer.consumeResultPages(pageUrl);
    } else if (pageUrl.getType().equals(PageType.GARAGE_DETAILS)) {
      garageDetailsQueueConsumer.consumeGarageDetails(pageUrl);
    }
  }

  private void doGarageJob(Job job) {
    Garage garage = (Garage)job.getJob();

    garagePersister.persist(garage);
  }
}
