package com.example.demo.props;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SeleniumConfigReader {
    private static Properties properties;


    static {
        properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(new File("src/test/resources/config.properties"));
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
