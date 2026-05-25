package com.epam.campus.selenium.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input != null) properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String key) {
        return properties.getProperty(key);
    }
}
