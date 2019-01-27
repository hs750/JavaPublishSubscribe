package com.harrison.pubsub;

@FunctionalInterface
public interface Publisher {

  boolean isPublishLoopbackAllowed();
}
