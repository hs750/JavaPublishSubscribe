package com.harrison.pubsub.config;

import java.util.List;

@FunctionalInterface
public interface ConfigReader {
    List<ConfigItem> readConfig();
}
