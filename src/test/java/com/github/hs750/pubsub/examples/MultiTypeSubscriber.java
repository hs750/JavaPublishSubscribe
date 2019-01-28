package com.github.hs750.pubsub.examples;

import com.github.hs750.pubsub.PublishSubscribeService;
import com.github.hs750.pubsub.Subscriber;
import com.github.hs750.pubsub.helpers.TestingCallback;

/**
 * An example of subscribing to multiple data types in one class (in this case, {@link String} and
 * {@link Integer}). It is not possible to implement {@link Subscriber} twice
 * with different type parameters. Instead this class has implementations of the multiple receive
 * methods it would have had but then uses them lambda method references into {@link
 * PublishSubscribeService#subscribe(Subscriber, Class)}
 */
public class MultiTypeSubscriber {

  private TestingCallback callback;

  public MultiTypeSubscriber(TestingCallback callback) {
    this.callback = callback;
    subscribe();
  }

  /**
   * Use the {@link PublishSubscribeService} to subscribe to Strings and Integers via use of lambda
   * method references.
   */
  private void subscribe() {
    PublishSubscribeService.subscribe(this::receive, String.class);
    PublishSubscribeService.subscribe(this::receive, Integer.class);
  }

  /**
   * Act on receiving String data from subscriptions.
   *
   * @param data Data received.
   */
  public void receive(String data) {
    this.callback.callback(data);
  }

  /**
   * Act on receiving Integer data from subscriptions.
   *
   * @param data Data received.
   */
  public void receive(Integer data) {
    this.callback.callback(data);
  }

}
