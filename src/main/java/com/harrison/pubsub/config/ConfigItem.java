package com.harrison.pubsub.config;

import java.util.List;

public class ConfigItem {
    private final Class<?> clazz;
    private final List<String> args;

    public ConfigItem(Class<?> clazz, List<String> args){
        this.clazz = clazz;
        this.args = args;
    }

    public Class<?> getConfigClass(){
        return clazz;
    }

    public List<String> getArgs(){
        return args;
    }
}
