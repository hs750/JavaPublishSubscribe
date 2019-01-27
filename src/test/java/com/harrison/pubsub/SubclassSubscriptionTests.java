package com.harrison.pubsub;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;

public class SubclassSubscriptionTests {

  @Test
  @SuppressWarnings("unchecked")
  public void testReceiveSubclassOnSuperClassSubscription() {
    Subscriber<Object> subscriber = mock(Subscriber.class);
    PublishSubscribeService.subscribe(subscriber, Object.class);

    PublishSubscribeService.publish("Test Data", () -> true);

    verify(subscriber, times(1)).receive("Test Data");
  }
}
