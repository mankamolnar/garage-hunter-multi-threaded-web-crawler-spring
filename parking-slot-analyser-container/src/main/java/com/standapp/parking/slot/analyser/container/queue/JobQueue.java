package com.standapp.parking.slot.analyser.container.queue;

import com.standapp.parking.slot.analyser.domain.queue.Job;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class JobQueue {

  private Map<Job, Boolean> jobs;

  public JobQueue() {
    this.jobs = new ConcurrentHashMap<>();
  }

  public void add(Job job) {
    if (!jobs.containsKey(job)) {
      jobs.put(job, true);
    }
  }

  public Optional<Job> removeFirst() {
    Optional<Job> optionalPageUrl = getFirst();
    optionalPageUrl.ifPresent(pageUrl -> jobs.remove(pageUrl));

    return optionalPageUrl;
  }

  public boolean isEmpty() {
    return !getFirst().isPresent();
  }

  private Optional<Job> getFirst() {
    return jobs.keySet()
        .stream()
        .findFirst();
  }
}
