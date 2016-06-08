package com.harrison.pubsub;

import com.harrison.pubsub.interfaces.Publisher;
import com.harrison.pubsub.interfaces.Subscriber;

import javax.swing.JPanel;
import java.util.List;

public abstract class PubSubPanel extends JPanel implements Publisher, Subscriber{
    private static final long serialVersionUID = 6289206719300193485L;
    private PubSubInterface psi;

    public PubSubPanel(List<String> args){
        psi = new PubSubComponent(args);
    }

    public PubSubPanel(boolean allowPublishLoopback){
        psi = new PubSubComponent(allowPublishLoopback);
    }

    public PubSubPanel(){
        psi = new PubSubComponent();
    }

    public void subscribe(Class<?> clazz){
        psi.subscribe(clazz);
    }

    public void publish(Object data){
        psi.publish(data);
    }

    public abstract void receive(Object data);

    @Override
    public boolean isPublishLoopbackAllowed() {
        return psi.isPublishLoopbackAllowed();
    }

    private class PubSubComponent extends PubSubInterface{

        public PubSubComponent(List<String> args){
            super(args);
        }

        public PubSubComponent(boolean allowPublishLoopback){
            super(allowPublishLoopback);
        }

        public PubSubComponent(){
            super();
        }

        @Override
        public void receive(Object data) {
            PubSubPanel.this.receive(data);
        }
    }

}
