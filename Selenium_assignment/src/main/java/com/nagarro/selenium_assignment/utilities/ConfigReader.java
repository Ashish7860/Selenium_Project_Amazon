package com.nagarro.selenium_assignment.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private Properties properties;

    public ConfigReader() {
        loadProperties();
    }

    private void loadProperties() {
        properties = new Properties();
        
        // Load the configuration file from src/test/resources using the class loader
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (inputStream == null) {
                throw new RuntimeException("Configuration file 'config.properties' not found in the classpath.");
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file: ", e);
        }
    }

    public String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property '" + key + "' not found in configuration file");
        }
        return value;
    }
    
    
    
    public int getGlobalWaitTime() {
        try {
        	return Integer.parseInt(properties.getProperty("global.wait.time"));
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid number format for globalWaitTime in configuration file", e);
        }
    }
}
