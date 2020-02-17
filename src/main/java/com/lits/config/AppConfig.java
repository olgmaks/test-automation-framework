package com.lits.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppConfig {

    private Properties properties;

    public AppConfig() {

        this.properties = new Properties();

        try {
            properties.load(new FileInputStream(new File("src/main/resources/application.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getBaseUrl() {

        return properties.getProperty("base.url");
    }

    public String getUserEmail() {

        return properties.getProperty("user.email");
    }

    public String getUserPassword() {

        return properties.getProperty("user.password");
    }
}
