package com.harrison.pubsub;

@FunctionalInterface
public interface Subscriber<T> {

  void receive(T data);
}
