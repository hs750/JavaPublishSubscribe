package com.harrison.pubsub.interfaces;

public interface Publisher {
    void publish(Object data);
    boolean isPublishLoopbackAllowed();
}
