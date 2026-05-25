package com.epam.campus.selenium.config;

import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Properties;

public class ConfigReader {
    protected Properties properties;

    public ConfigReader(String env_name) {
        properties = new Properties();
        try {
            String filePath = String.format("src/main/resources/config/%s.properties", env_name);
            FileInputStream file = new FileInputStream(filePath);
            properties.load(file);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config file", e);
        }
    }

    public String getEnv(String key) {
        return properties.getProperty(key);
    }

}
