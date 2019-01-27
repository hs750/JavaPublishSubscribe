package com.harrison.pubsub;

import java.util.HashMap;
import java.util.Map;

public final class PublishSubscribeService {

  private static Map<Class<?>, PublishSubscribeManager<?>> publishSubscribeManagers =
      new HashMap<>();

  @SuppressWarnings("unchecked")
  public static <T> void subscribe(Subscriber<T> subscriber, Class<T> subscribedClass) {
    PublishSubscribeManager<T> manager = (PublishSubscribeManager<T>) publishSubscribeManagers
        .get(subscribedClass);
    if (manager == null) {
      manager = new PublishSubscribeManager<>();
      publishSubscribeManagers.put(subscribedClass, manager);
    }
    manager.addSubscriber(subscriber);
  }


  @SuppressWarnings("unchecked")
  public static <T> void publish(T data, Publisher publisher) {
    Class<?> dataClass = data.getClass();
    while (dataClass != null) {
      PublishSubscribeManager psm = publishSubscribeManagers.get(dataClass);
      if (psm != null) {
        psm.broadcastData(data, publisher);
      }
      dataClass = dataClass.getSuperclass();
    }
  }

}
