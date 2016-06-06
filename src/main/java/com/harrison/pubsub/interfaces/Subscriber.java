package com.harrison.pubsub.interfaces;

public interface Subscriber {
    void subscribe(Class<?> clazz);
    void receive(Object data);
}
