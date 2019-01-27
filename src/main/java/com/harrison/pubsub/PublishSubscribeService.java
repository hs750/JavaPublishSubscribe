package com.harrison.pubsub;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;

public final class PublishSubscribeService {

  private static HashMap<Class<?>, PublishSubscribeManager<?>> publishSubscribeManagers =
      new HashMap<>();

  @SuppressWarnings("unchecked")
  public static <T> void subscribe(Subscriber<T> subscriber) {
    Type subscribedType = subscriber.getClass().getGenericSuperclass();
    Class<T> subscribedClass = (Class<T>) ((ParameterizedType) subscribedType)
        .getActualTypeArguments()[0];
    PublishSubscribeManager<T> manager = (PublishSubscribeManager<T>) publishSubscribeManagers
        .getOrDefault(subscribedClass, new PublishSubscribeManager(subscribedClass));
    manager.addSubscriber(subscriber);
  }


  @SuppressWarnings("unchecked")
  public static <T> void publish(T data, Publisher publisher) {
    Class<?> dataClass = data.getClass();
    PublishSubscribeManager psm = publishSubscribeManagers.get(dataClass);
    if (psm != null) {
      psm.broadcastData(data, publisher);
    }
  }

}
