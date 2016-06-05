package com.harrison.pubsub;

import java.util.HashSet;

public final class PubSubManager {
    private Class<?> manages;
    private HashSet<PubSubInterface> subscribers = new HashSet<>();

    public PubSubManager(Class<?> clazz){
        manages = clazz;
    }

    public void addSubscriber(PubSubInterface intf){
        subscribers.add(intf);
    }

    public void broadcastData(Object data, PubSubInterface sender){
        for(PubSubInterface subscriber : subscribers){
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
