package com.harrison.pubsub;

import java.util.HashSet;
import java.util.Set;

final class PublishSubscribeManager<T> {

  private Set<Subscriber<T>> subscribers = new HashSet<>();

  void addSubscriber(Subscriber<T> subscriber) {
    subscribers.add(subscriber);
  }

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
