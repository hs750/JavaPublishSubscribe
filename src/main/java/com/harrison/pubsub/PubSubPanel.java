package com.harrison.pubsub;

import javax.swing.JPanel;

public abstract class PubSubPanel extends JPanel{
    private static final long serialVersionUID = 6289206719300193485L;
    private PubSubInterface psi = new PubSubInterface() {

        @Override
        public void receive(Object data) {
            PubSubPanel.this.receive(data);
        }
    };

    public void subscribe(Class<?> clazz){
        psi.subscribe(clazz);
    }

    public void publish(Object data){
        psi.publish(data);
    }

    public abstract void receive(Object data);

}
