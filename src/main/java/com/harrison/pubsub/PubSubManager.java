package com.harrison.pubsub;

import com.harrison.pubsub.interfaces.Publisher;
import com.harrison.pubsub.interfaces.Subscriber;

import java.util.HashSet;

public final class PubSubManager {
    private Class<?> manages;
    private HashSet<Subscriber> subscribers = new HashSet<>();

    public PubSubManager(Class<?> clazz){
        manages = clazz;
    }

    public void addSubscriber(Subscriber subscriber){
        subscribers.add(subscriber);
    }

    public void broadcastData(Object data, Publisher sender){
        for(Subscriber subscriber : subscribers){
            if(sender == subscriber){
                if(sender.isPublishLoopbackAllowed()){
                    subscriber.receive(data);
                }
            }else{
                subscriber.receive(data);
            }
        }
    }

    public boolean manages(Class<?> clazz){
        return manages.equals(clazz);
    }


}
