
package com.demo.utils;



import com.demo.constants.FrameworkConstants;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public final class ConfigUtil {

    private ConfigUtil(){

    }

    private static final Properties properties = new Properties();
    private static final Map<String, String> CONFIGMAP = new HashMap<>();

    static {
        boolean envFlag = false;
        String envValue="";
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(FrameworkConstants.PROPERTY_PATH));
            properties.load(reader);
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            if (String.valueOf(entry.getKey()).contains("env_resources")) {
                envFlag = true;
                envValue=String.valueOf(entry.getValue()).toUpperCase();
            }



            CONFIGMAP.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));

        }
        if (envFlag) {
            try {
                reader = new BufferedReader(new FileReader(FrameworkConstants.TEST_RESOURCE_PATH+ File.separator+envValue+".properties"));
                properties.load(reader);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                CONFIGMAP.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));

            }
        }



    }

    public static String getValue(String key) {

        if (Objects.isNull(key) || Objects.isNull(CONFIGMAP.get(key))) {
            throw new RuntimeException("Key not present in properties file");
        }
        return CONFIGMAP.get(key);

    }

}