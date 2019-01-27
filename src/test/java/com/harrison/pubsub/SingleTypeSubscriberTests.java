package com.harrison.pubsub;

import com.harrison.pubsub.examples.SingleTypeSubscriber;
import com.harrison.pubsub.helpers.TestingCallback;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class SingleTypeSubscriberTests {

  @Test
  public void testCallbackCalledOnReceive() {
    TestingCallback callback = mock(TestingCallback.class);
    SingleTypeSubscriber sts = new SingleTypeSubscriber(callback);

    sts.receive("Test Data");

    verify(callback, times(1)).callback("Test Data");
  }

  @Test
  public void testCallbackCalledOnReceiveFromPublish() {
    TestingCallback callback = mock(TestingCallback.class);
    new SingleTypeSubscriber(callback);

    PublishSubscribeService.publish("Test Data", () -> true);

    verify(callback, times(1)).callback("Test Data");
  }
}
