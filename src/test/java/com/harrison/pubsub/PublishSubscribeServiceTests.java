package com.harrison.pubsub;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class PublishSubscribeServiceTests {

  @Test
  @SuppressWarnings("unchecked")
  public void testSubscriptionPublishRoundTrip() {
    PublishSubscriber<String> publishSubscriber = mock(PublishSubscriber.class);
    when(publishSubscriber.isPublishLoopbackAllowed()).thenReturn(true);

    PublishSubscribeService.subscribe(publishSubscriber, String.class);
    PublishSubscribeService.publish("Test Data", publishSubscriber);

    verify(publishSubscriber, times(1)).receive("Test Data");
  }

  @Test
  public void testPublishNoSubscribers() {
    Publisher publisher = mock(Publisher.class);
    PublishSubscribeService.publish("Test Data", publisher);

    verify(publisher, times(0)).isPublishLoopbackAllowed();
    // No exceptions from no subscribers
  }

  @Test
  @SuppressWarnings("unchecked")
  public void testPublishNoMatchingSubscribers() {
    Publisher publisher = mock(Publisher.class);
    Subscriber<Boolean> subscriber = mock(Subscriber.class);

    PublishSubscribeService.subscribe(subscriber, Boolean.class);
    PublishSubscribeService.publish("Test Data", publisher);

    verify(publisher, times(0)).isPublishLoopbackAllowed();
    verify(subscriber, times(0)).receive(anyBoolean());
    // No exceptions from no subscribers
  }
}
