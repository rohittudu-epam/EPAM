package com.epam.campus.selenium.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigReader {

    private static final Logger logger = LoggerFactory.getLogger(ConfigReader.class);
    private static final Properties properties = new Properties();

    private ConfigReader() {
    }

    public static void initialize() {
        try (InputStream input = ConfigReader.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IllegalStateException("config.properties not found in classpath");
            }
            properties.load(input);
            logger.info("Configuration loaded successfully ({} properties)", properties.size());
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load config.properties", e);
        }
    }

    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new IllegalArgumentException("Property '" + key + "' not found in config.properties");
        }
        return value.trim();
    }

    public static int getIntProperty(String key, int defaultValue) {
        String value = properties.getProperty(key);
        if (value == null) {
            return defaultValue;
        }
        return Integer.parseInt(value.trim());
    }

    public static boolean getBooleanProperty(String key, boolean defaultValue) {
        String value = properties.getProperty(key);
        if (value == null) {
            return defaultValue;
        }
        return Boolean.parseBoolean(value.trim());
    }
}
