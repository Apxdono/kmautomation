package com.kmware.automation.io.utils;

import org.apache.commons.codec.Charsets;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Oleg
 * Date: 26.07.13 2:04
 */
public class PropertiesHelper {

    protected Properties properties;

    /**
     * Load properties from classpath and store them in current PropertyHelper instance;
     *
     * @param file  - file to load
     * @param store - flag that indicates whether to store properties in instance or just load and return them.
     * @return loaded properties
     */
    public Properties load(String file, boolean store) {

        Properties props = new Properties();
        InputStream is = getClass().getClassLoader().getResourceAsStream(file);

        if (is != null) {
            try {
                props.load(new InputStreamReader(is, Charsets.UTF_8));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new NullPointerException("No property file resource found with name: " + file + ". Execution can't continue.");
        }
        if (store) {
            properties = props;
        }
        return props;
    }

    public String property(String key) {
        return properties.getProperty(key);
    }

    public String property(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public String property(Enum key) {
        return properties.getProperty(key.toString());
    }

    public String property(Enum key, String defaultValue) {
        return properties.getProperty(key.toString(), defaultValue);
    }
}

