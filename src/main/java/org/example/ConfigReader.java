package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;
    static {
        try {
            InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream("configuration.properties");
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getProperty(String propName) {
        return properties.getProperty(propName);
    }

}
