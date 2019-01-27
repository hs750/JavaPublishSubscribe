package com.harrison.pubsub;

import java.util.HashSet;
import java.util.Set;

/**
 * Manages the {@link Subscriber}s of a particular type.
 *
 * @param <T> The type of data being subscribed to by managed {@link Subscriber}s.
 */
final class SubscriptionManager<T> {

  private Set<Subscriber<T>> subscribers = new HashSet<>();

  /**
   * Add a {@link Subscriber} to be managed.
   *
   * @param subscriber The {@link Subscriber} to manage.
   */
  void addSubscriber(Subscriber<T> subscriber) {
    subscribers.add(subscriber);
  }

  /**
   * Send data to all managed {@link Subscriber}s. Don't send data back to the publisher unless
   * {@link Publisher#isPublishLoopbackAllowed()} is true.
   *
   * @param data The data being published.
   * @param sender The {@link Publisher} that sent the data.
   */
  void broadcastData(T data, Publisher sender) {
    for (Subscriber<T> subscriber : subscribers) {
      if (sender == subscriber) {
        if (sender.isPublishLoopbackAllowed()) {
          subscriber.receive(data);
        }
      } else {
        subscriber.receive(data);
      }
    }
  }
}
