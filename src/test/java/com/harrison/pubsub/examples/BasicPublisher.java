package com.harrison.pubsub.examples;

import com.harrison.pubsub.PublishSubscribeService;
import com.harrison.pubsub.Publisher;

/**
 * An example of a class implementing {@link Publisher} that uses the {@link
 * PublishSubscribeService} to publish data.
 */
public class BasicPublisher implements Publisher {

  @Override
  public boolean isPublishLoopbackAllowed() {
    return false;
  }

  /**
   * Publish the {@link Integer}s 0 to 9.
   */
  public void publishData() {
    for (int i = 0; i < 10; i++) {
      PublishSubscribeService.publish(i, this);
    }
  }
}
