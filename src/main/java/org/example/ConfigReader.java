package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static ConfigReader instance;
    private Properties properties;

    private ConfigReader() {
        try {
            InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream("configuration.properties");
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static synchronized ConfigReader getInstance() {
        if(instance == null) {
            instance = new ConfigReader();
        }
        return instance;
    }
    public String getProperty(String propName) {
        return properties.getProperty(propName);
    }

}
