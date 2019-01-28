# JavaPublishSubscribe

A lightweight, pure java publish-subscribe framework. A simple API allows objects within an application using this framework to synchronously publish and subscribe to any Java type. 

## Usage
All classes that use the publish subscribe framework must implement either `Publisher` or `Subscriber` for publishing or subscribing to data respectively.

To subscribe to a datatype (e.g. `String`) a `Subscriber` must call `PublishSubscribeService.subscribe(this, String.class)`, usually in its constructor.

To publish data a `Publisher` must call `PublishSubscriberService.publish(data, this)`.

A `Subscriber` implements the method `receive(T data)`. This is the method called when the subscriber has subscribed to the type `T` and that type has been published elseware.

A `Publisher` implements `isPublishLoopbackAllowed()`. This method determines whether when this publisher publishes data it also subscribes to if its `receive` method will be called (aka the data has looped back to itself).
