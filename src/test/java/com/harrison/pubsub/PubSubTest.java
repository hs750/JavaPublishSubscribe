package com.harrison.pubsub;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class PubSubTest extends PubSubInterface{

    public static void main(String[] args){
        PubSubTest t1 = new PubSubTest("Test 1");
        PubSubTest t2 = new PubSubTest("Test 2");

        JFrame f = new JFrame();
        f.setSize(1000, 1000);


        f.add(t1.new test2("test 3"));
        f.add(t1.new test2("test 4"));

        f.show();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.send();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.send();

    }

    String a;

    public PubSubTest(String arg){
        a = arg;
        subscribe(String.class);
    }

    public void send(){
        publish(a);
    }

    @Override
    public void receive(Object data) {
        System.out.println(a + " received " + data);

    }


    public class test2 extends PubSubPanel{
        private String b;
        private JLabel l;
        public test2(String arg){
            b = arg;
            l = new JLabel("Hello " + b);
            setSize(200, 200);
            add(l);
            subscribe(String.class);
        }



        @Override
        public void receive(Object data) {
            l.setText(l.getText() + " - " + data);

        }

    }

}
