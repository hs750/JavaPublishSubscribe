package com.harrison.pubsub;

public interface Subscriber<T> {
    void receive(T data);
}
