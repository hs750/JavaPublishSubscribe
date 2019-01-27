package com.harrison.pubsub;

import java.util.HashSet;

final class PublishSubscribeManager<T> {

  private Class<T> manages;
  private HashSet<Subscriber<T>> subscribers = new HashSet<>();

  PublishSubscribeManager(Class<T> clazz) {
    manages = clazz;
  }

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
