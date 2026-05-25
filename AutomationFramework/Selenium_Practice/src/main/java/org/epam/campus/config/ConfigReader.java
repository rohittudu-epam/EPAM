package org.epam.campus.config;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    private Properties properties;

    public ConfigReader(String env){
        properties = new Properties();
        try {
            String filePath = String.format("D:/Old Files/AutomationFramework/Selenium_Practice/src/main/resources/config/%s.properties", env);
            FileInputStream fileInput = new FileInputStream(filePath);
            properties.load(fileInput);
        } catch (Exception e) {
            throw new RuntimeException("Error Reading Config File ", e);
        }
    }

    public String get(String property){
        return properties.getProperty(property);
    }
}
