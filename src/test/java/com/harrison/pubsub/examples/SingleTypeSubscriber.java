package com.harrison.pubsub.examples;

import com.harrison.pubsub.PublishSubscribeService;
import com.harrison.pubsub.Subscriber;
import com.harrison.pubsub.helpers.TestingCallback;

public class SingleTypeSubscriber implements Subscriber<String> {

  private TestingCallback callback;

  public SingleTypeSubscriber(TestingCallback callback) {
    this.callback = callback;
    subscribe();
  }

  @Override
  public void receive(String data) {
    this.callback.callback(data);
  }

  private void subscribe(){
    PublishSubscribeService.subscribe(this, String.class);
  }
}
