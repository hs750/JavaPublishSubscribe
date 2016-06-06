package com.harrison.pubsub;

import com.harrison.pubsub.interfaces.Publisher;
import com.harrison.pubsub.interfaces.Subscriber;

import java.util.HashMap;
import java.util.List;

public abstract class PubSubInterface implements Publisher, Subscriber{
    private static HashMap<Class<?>, PubSubManager> pubSubManagers = new HashMap<>();
    private boolean allowPublishLoopback;

    public PubSubInterface(){
        allowPublishLoopback = false;
    }

    public PubSubInterface(boolean allowPublishLoopback){
        this.allowPublishLoopback = allowPublishLoopback;
    }

    public PubSubInterface(List<String> args){
        allowPublishLoopback = args.contains("-l");
    }

    public void subscribe(Class<?> clazz){
        PubSubManager psm = pubSubManagers.get(clazz);
        if(psm == null){
            psm = new PubSubManager(clazz);
            pubSubManagers.put(clazz, psm);
        }
        psm.addSubscriber(this);

    }

    public void publish(Object data){
        Class<?> dataClass = data.getClass();
        PubSubManager psm = pubSubManagers.get(dataClass);
        if(psm != null){
            psm.broadcastData(data, this);
        }

    }

    public boolean isPublishLoopbackAllowed(){
        return allowPublishLoopback;
    }

    public abstract void receive(Object data);

}
