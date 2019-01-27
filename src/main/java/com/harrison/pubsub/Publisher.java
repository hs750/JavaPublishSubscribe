package com.harrison.pubsub;

/**
 * Implementations of this interface publish data using the {@link PublishSubscribeService}.
 */
@FunctionalInterface
public interface Publisher {

  /**
   * If an instance of this Publisher is also a {@link Subscriber}, whether data published by the
   * instance is allowed to also be received by the same instance.
   *
   * @return If publish-subscribe loopback is allowed to this instance.
   */
  boolean isPublishLoopbackAllowed();
}
