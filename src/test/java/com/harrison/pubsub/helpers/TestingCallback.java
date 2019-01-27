package com.harrison.pubsub.helpers;

/**
 * This interface is used when testing to for callbacks from example subscriber receive methods.
 */
public interface TestingCallback {

  void callback(Object data);
}
