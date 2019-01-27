package com.harrison.pubsub;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.harrison.pubsub.examples.MultiTypeSubscriber;
import com.harrison.pubsub.examples.SingleTypeSubscriber;
import com.harrison.pubsub.helpers.TestingCallback;
import org.junit.Test;

public class MultiTypeSubscriberTests {

  @Test
  public void testCallbackCalledOnReceiveType1() {
    TestingCallback callback = mock(TestingCallback.class);
    MultiTypeSubscriber sts = new MultiTypeSubscriber(callback);

    sts.receive("Test Data");

    verify(callback, times(1)).callback("Test Data");
  }

  @Test
  public void testCallbackCalledOnReceiveType2() {
    TestingCallback callback = mock(TestingCallback.class);
    MultiTypeSubscriber sts = new MultiTypeSubscriber(callback);

    sts.receive(1);

    verify(callback, times(1)).callback(1);
  }


  @Test
  public void testCallbackCalledOnReceiveFromPublishType1() {
    TestingCallback callback = mock(TestingCallback.class);
    new MultiTypeSubscriber(callback);

    PublishSubscribeService.publish("Test Data", () -> true);

    verify(callback, times(1)).callback("Test Data");
  }

  @Test
  public void testCallbackCalledOnReceiveFromPublishType2() {
    TestingCallback callback = mock(TestingCallback.class);
    new MultiTypeSubscriber(callback);

    PublishSubscribeService.publish(1, () -> true);

    verify(callback, times(1)).callback(1);
  }
}
