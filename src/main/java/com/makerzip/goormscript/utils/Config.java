package com.makerzip.goormscript.utils;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class Config {
    public static String _prefix = "/gs";

    public String token(boolean debug) {
        Properties prop = new Properties();
        URL url = Thread.currentThread().getContextClassLoader().getResource("config.properties");
        if (debug) {
            if (prop.getProperty("test-token").length() == 59) {
                try {
                    prop.load(url.openStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return prop.getProperty("test-token");
            } else {
                return prop.getProperty("main-token");
            }

        } else {
            try {
                prop.load(url.openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return prop.getProperty("main-token");
        }
    }
}
