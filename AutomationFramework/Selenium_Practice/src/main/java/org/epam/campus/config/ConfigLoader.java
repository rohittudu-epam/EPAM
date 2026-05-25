package org.epam.campus.config;

public class ConfigLoader {
    protected static ConfigReader configReader;

    static {
        String fileName = System.getProperty("env", "dev");
        configReader = new ConfigReader(fileName);
    }

    public String getProp(String prop){
        return configReader.get(prop);

    }
}
