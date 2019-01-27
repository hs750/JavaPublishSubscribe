package com.harrison.pubsub.examples;

import com.harrison.pubsub.PublishSubscribeService;
import com.harrison.pubsub.Subscriber;
import com.harrison.pubsub.helpers.TestingCallback;

/**
 * An example of subscribing to a single type (in this case {@link String}).
 */
public class SingleTypeSubscriber implements Subscriber<String> {

  private TestingCallback callback;

  public SingleTypeSubscriber(TestingCallback callback) {
    this.callback = callback;
    subscribe();
  }

  @Override
  public void receive(String data) {
    this.callback.callback(data);
  }

  /**
   * Subscribe to data using {@link PublishSubscribeService#subscribe(Subscriber, Class)}.
   */
  private void subscribe() {
    PublishSubscribeService.subscribe(this, String.class);
  }
}
