package com.github.hs750.pubsub;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.github.hs750.pubsub.examples.BasicPublisher;
import org.junit.Test;

public class TestBasicPublisher {

  @Test
  @SuppressWarnings("unchecked")
  public void testPublishTenInts() {
    BasicPublisher publisher = new BasicPublisher();
    Subscriber<Integer> subscriber = mock(Subscriber.class);

    PublishSubscribeService.subscribe(subscriber, Integer.class);

    publisher.publishData();

    for (int i = 0; i < 10; i++) {
      verify(subscriber, times(1)).receive(i);
    }
  }
}
