package com.harrison.pubsub.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DefaultConfigReader implements ConfigReader {
    private File configFile;

    public DefaultConfigReader(String configPath){
        this.configFile = new File(configPath);
    }

    @Override
    public List<ConfigItem> readConfig() {
        ArrayList<ConfigItem> items = new ArrayList<>();
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(configFile))) {
                for (String line; (line = br.readLine()) != null; ) {
                    String[] splitLine = line.split(" ");
                    if(splitLine.length > 0) {
                        try {
                            Class<?> clazz = Class.forName(splitLine[0]);
                            ArrayList<String> args = new ArrayList<>();
                            for (int i = 1; i < splitLine.length; i++) {
                                if (splitLine[i].length() > 0) {
                                    args.add(splitLine[i]);
                                }
                            }
                            items.add(new ConfigItem(clazz, args));
                        }catch (ClassNotFoundException e){
                            System.err.println("Class not found: " + e.getMessage());
                        }
                    }
                }
            }
        }catch(IOException e){
            System.err.println("Unable to read config file. " + e.getMessage());
        }

        return items;
    }
}
