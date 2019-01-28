package com.github.hs750.pubsub;

import java.util.HashMap;
import java.util.Map;

/**
 * This static class is used to subscribe to, and publish, data.
 */
public final class PublishSubscribeService {

  private static Map<Class<?>, SubscriptionManager<?>> subscriptionManagers = new HashMap<>();

  /**
   * Subscribe a {@link Subscriber} to a data type.
   *
   * @param subscriber The {@link Subscriber} subscribing to the data type.
   * @param subscribedClass The {@link Class} being subscribed to.
   * @param <T> The data type being subscribed to.
   */
  @SuppressWarnings("unchecked")
  public static <T> void subscribe(Subscriber<T> subscriber, Class<T> subscribedClass) {
    SubscriptionManager<T> manager = (SubscriptionManager<T>) subscriptionManagers
        .get(subscribedClass);
    if (manager == null) {
      manager = new SubscriptionManager<>();
      subscriptionManagers.put(subscribedClass, manager);
    }
    manager.addSubscriber(subscriber);
  }


  /**
   * Publish data to all {@link Subscriber}s of the data type.
   * <br>
   * If a subscriber subscribes to a superclass of the data being published, they will also receive
   * the published data.
   *
   * @param data The data being published.
   * @param publisher The {@link Publisher} of the data.
   * @param <T> The type of the data being published.
   */
  @SuppressWarnings("unchecked")
  public static <T> void publish(T data, Publisher publisher) {
    Class<?> dataClass = data.getClass();
    while (dataClass != null) {
      SubscriptionManager sm = subscriptionManagers.get(dataClass);
      if (sm != null) {
        sm.broadcastData(data, publisher);
      }
      dataClass = dataClass.getSuperclass();
    }
  }
}
