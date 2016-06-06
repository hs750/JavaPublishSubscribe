package com.harrison.pubsub;

import com.harrison.pubsub.config.ConfigItem;
import com.harrison.pubsub.config.ConfigReader;
import com.harrison.pubsub.config.DefaultConfigReader;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class PubSubApp {
    public static final String DEFAULT_CONFIG_PATH = "config";
    private List<Object> apps;


    public PubSubApp(ConfigReader configReader){
        apps = new ArrayList<>();

        List<ConfigItem> configs = configReader.readConfig();
        for(ConfigItem app : configs){
            try {
                apps.add(app.getConfigClass().getDeclaredConstructor(List.class).newInstance(app.getArgs()));
                System.out.println("Creating " + app.getConfigClass().toString() + " with arguments " + app.getArgs());
            } catch (InstantiationException |
                    IllegalAccessException |
                    InvocationTargetException e){
                e.printStackTrace();
            }catch (NoSuchMethodException e) {
                System.err.println("Unable to initialise " + app.getConfigClass() + ", must have List<String> constructor");
            }
        }
    }

    public static void main(String... args){
        PubSubApp app;

        if(args.length > 0){
            app = new PubSubApp(new DefaultConfigReader(args[0]));
        }else{
            app = new PubSubApp(new DefaultConfigReader(DEFAULT_CONFIG_PATH));
        }
    }
}
