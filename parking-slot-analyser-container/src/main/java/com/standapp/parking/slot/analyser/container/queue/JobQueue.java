package com.standapp.parking.slot.analyser.container.queue;

import com.standapp.parking.slot.analyser.domain.queue.Job;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.springframework.stereotype.Service;

@Service
public class JobQueue {

  private ConcurrentLinkedQueue<Job> jobs = new ConcurrentLinkedQueue<>();

  public void add(Job job) {
    if (!jobs.contains(job)) {
      jobs.add(job);
    }
  }

  public Optional<Job> removeFirst() {
    return Optional.of(jobs.poll());
  }

  public boolean isEmpty() {
    return jobs.size() == 0;
  }
}
