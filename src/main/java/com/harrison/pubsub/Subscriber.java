package com.harrison.pubsub;

/**
 * Implementations of this interface subscribe to a data type via the {@link
 * PublishSubscribeService}.
 *
 * @param <T> The type of data being subscribed to.
 */
@FunctionalInterface
public interface Subscriber<T> {

  /**
   * Receive data published to the {@link PublishSubscribeService}.
   * @param data The data being received.
   */
  void receive(T data);
}
