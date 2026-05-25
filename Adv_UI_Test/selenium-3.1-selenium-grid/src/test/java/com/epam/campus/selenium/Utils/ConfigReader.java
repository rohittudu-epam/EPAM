package com.epam.campus.selenium.Utils;

import java.io.InputStream;
import java.util.Properties;

import com.epam.campus.selenium.Base.ExecutionMode;

public final class ConfigReader {
    private static final String CONFIG_FILE = "config.properties";
    private static final Properties PROPERTIES = loadProperties();

    private ConfigReader() {
    }

    public static String get(String key, String defaultValue) {
        String fromSystemProperty = System.getProperty(key);
        if (hasText(fromSystemProperty)) {
            return fromSystemProperty.trim();
        }

        String envKey = key.toUpperCase().replace('.', '_');
        String fromEnv = System.getenv(envKey);
        if (hasText(fromEnv)) {
            return fromEnv.trim();
        }

        String fromFile = PROPERTIES.getProperty(key);
        if (hasText(fromFile)) {
            return fromFile.trim();
        }

        return defaultValue;
    }

    public static String getRequired(String key) {
        String value = get(key, "");
        if (!hasText(value)) {
            throw new IllegalStateException("Missing required configuration: " + key);
        }
        return value;
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return Boolean.parseBoolean(get(key, String.valueOf(defaultValue)));
    }

    public static ExecutionMode getExecutionMode() {
        String rawMode = get("execution.mode", "");
        if (!hasText(rawMode)) {
            rawMode = get("executionMode", "local");
        }
        try {
            return ExecutionMode.valueOf(rawMode.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalStateException("Unsupported execution mode value: " + rawMode, ex);
        }
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (inputStream != null) {
                properties.load(inputStream);
            }
        } catch (Exception ex) {
            throw new IllegalStateException("Unable to load " + CONFIG_FILE, ex);
        }
        return properties;
    }

    private static boolean hasText(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
