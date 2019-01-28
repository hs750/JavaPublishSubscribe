package com.github.hs750.pubsub;

/**
 * This interface is for convinience. Implementing this interface results in implementing both
 * {@link Subscriber} and {@link Publisher}.
 *
 * @param <T> The type being subscribed to.
 */
public interface PublishSubscriber<T> extends Subscriber<T>, Publisher {

}
