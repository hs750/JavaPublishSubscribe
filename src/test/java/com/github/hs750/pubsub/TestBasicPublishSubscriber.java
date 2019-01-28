package com.github.hs750.pubsub;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.github.hs750.pubsub.examples.BasicPublishSubscriber;
import com.github.hs750.pubsub.helpers.TestingCallback;
import org.junit.Test;

public class TestBasicPublishSubscriber {

  @Test
  public void testPublishTenInts() {
    TestingCallback callback = mock(TestingCallback.class);
    BasicPublishSubscriber publishSubscriber = new BasicPublishSubscriber(callback);

    publishSubscriber.publish("Test Data");

    verify(callback, times(1)).callback("Test Data");
  }
}
