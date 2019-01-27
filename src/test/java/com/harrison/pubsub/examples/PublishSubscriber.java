package com.harrison.pubsub.examples;

import com.harrison.pubsub.Publisher;
import com.harrison.pubsub.Subscriber;

public interface PublishSubscriber<T> extends Subscriber<T>, Publisher {

}
