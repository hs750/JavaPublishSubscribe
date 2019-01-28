package com.github.hs750.pubsub.examples;

import com.github.hs750.pubsub.PublishSubscribeService;
import com.github.hs750.pubsub.PublishSubscriber;
import com.github.hs750.pubsub.Publisher;
import com.github.hs750.pubsub.Subscriber;
import com.github.hs750.pubsub.helpers.TestingCallback;

/**
 * An example of a class that implements both {@link Publisher} and {@link
 * Subscriber} (via {@link PublishSubscriber}. This class subscribes to and
 * publishes {@link String}s.
 */
public class BasicPublishSubscriber implements PublishSubscriber<String> {

  private TestingCallback callback;

  public BasicPublishSubscriber(TestingCallback callback) {
    this.callback = callback;

    // Subscribe to Strings
    PublishSubscribeService.subscribe(this, String.class);
  }

  @Override
  public boolean isPublishLoopbackAllowed() {
    return true; // When publishing a String, this objects receive method will be called.
  }

  @Override
  public void receive(String data) {
    this.callback.callback(data);
  }

  /**
   * Publish data from this object.
   *
   * @param data The data to publish.
   */
  public void publish(String data) {
    PublishSubscribeService.publish(data, this);
  }
}
