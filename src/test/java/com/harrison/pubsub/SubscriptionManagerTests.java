package com.harrison.pubsub;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class SubscriptionManagerTests {

  @Test
  public void testBroadcastNoSubscribers() {
    Publisher publisher = mock(Publisher.class);

    SubscriptionManager<String> sm = new SubscriptionManager<>();

    sm.broadcastData("Test Data", publisher);

    verify(publisher, times(0)).isPublishLoopbackAllowed();
  }

  @Test
  @SuppressWarnings("unchecked")
  public void testBroadcastOneSubscriber() {
    Publisher publisher = mock(Publisher.class);
    Subscriber<String> subscriber = mock(Subscriber.class);

    SubscriptionManager<String> sm = new SubscriptionManager<>();
    sm.addSubscriber(subscriber);

    sm.broadcastData("Test Data", publisher);

    verify(publisher, times(0)).isPublishLoopbackAllowed();
    verify(subscriber, times(1)).receive("Test Data");
  }

  @Test
  @SuppressWarnings("unchecked")
  public void testBroadcastTwoSubscribers() {
    Publisher publisher = mock(Publisher.class);
    Subscriber<String> subscriber1 = mock(Subscriber.class);
    Subscriber<String> subscriber2 = mock(Subscriber.class);

    SubscriptionManager<String> sm = new SubscriptionManager<>();
    sm.addSubscriber(subscriber1);
    sm.addSubscriber(subscriber2);

    sm.broadcastData("Test Data", publisher);

    verify(publisher, times(0)).isPublishLoopbackAllowed();
    verify(subscriber1, times(1)).receive("Test Data");
    verify(subscriber2, times(1)).receive("Test Data");
  }

  @Test
  @SuppressWarnings("unchecked")
  public void testBroadcastSubscriberNoLoopback() {
    PublishSubscriber<String> publishSubscriber = mock(PublishSubscriber.class);
    when(publishSubscriber.isPublishLoopbackAllowed()).thenReturn(false);

    SubscriptionManager<String> sm = new SubscriptionManager<>();
    sm.addSubscriber(publishSubscriber);

    sm.broadcastData("Test Data", publishSubscriber);

    verify(publishSubscriber, times(1)).isPublishLoopbackAllowed();
    verify(publishSubscriber, times(0)).receive(anyString());
  }

  @Test
  @SuppressWarnings("unchecked")
  public void testBroadcastSubscriberLoopback() {
    PublishSubscriber<String> publishSubscriber = mock(PublishSubscriber.class);
    when(publishSubscriber.isPublishLoopbackAllowed()).thenReturn(true);

    SubscriptionManager<String> sm = new SubscriptionManager<>();
    sm.addSubscriber(publishSubscriber);

    sm.broadcastData("Test Data", publishSubscriber);

    verify(publishSubscriber, times(1)).isPublishLoopbackAllowed();
    verify(publishSubscriber, times(1)).receive("Test Data");
  }

}
