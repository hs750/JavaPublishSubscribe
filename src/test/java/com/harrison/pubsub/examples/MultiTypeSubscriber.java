package com.harrison.pubsub.examples;

import com.harrison.pubsub.PublishSubscribeService;
import com.harrison.pubsub.helpers.TestingCallback;

public class MultiTypeSubscriber {

  private TestingCallback callback;

  public MultiTypeSubscriber(TestingCallback callback) {
    this.callback = callback;
    subscribe();
  }

  private void subscribe() {
    PublishSubscribeService.subscribe(this::receive, String.class);
    PublishSubscribeService.subscribe(this::receive, Integer.class);
  }

  public void receive(String data) {
    this.callback.callback(data);
  }

  public void receive(Integer data) {
    this.callback.callback(data);
  }

}
