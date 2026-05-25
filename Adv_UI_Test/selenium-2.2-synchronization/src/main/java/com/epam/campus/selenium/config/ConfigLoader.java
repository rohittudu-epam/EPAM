package com.epam.campus.selenium.config;

public class ConfigLoader {
    private static ConfigReader reader;

    static {
        String env = System.getProperty("env", "dev");
        reader = new ConfigReader(env);
    }

    public static String getProp(String prop){
        return reader.getEnv(prop);
    }
}
