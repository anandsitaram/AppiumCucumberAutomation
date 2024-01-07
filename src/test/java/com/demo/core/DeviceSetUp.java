package com.demo.core;

import com.demo.constants.FrameworkConstants;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings("ALL")
public final class DeviceSetUp {

   private static final Map<String, Map<String,Object>> DEVICECONFIG = new HashMap<>();
    public static void initDeviceConfig(){
        BufferedReader reader;
        Yaml yaml = new Yaml();
        try {
            reader = new BufferedReader(new FileReader(FrameworkConstants.YML_PATH));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
        Map<String, Object> mapYaml = yaml.load(reader);
        if(Objects.isNull(mapYaml.get("platforms"))){
            throw new RuntimeException("Platforms Details not present in config Device Yaml file");
        }
        List<Map<String, Object>> platformsList = (List<Map<String, Object>>) mapYaml.get("platforms");
        for(Map<String, Object> mp:platformsList){
            for (Map.Entry<String, Object> entry : mapYaml.entrySet()) {
                    if (!entry.getKey().equals("platforms")) {
                        mp.put(entry.getKey(), entry.getValue());
                    }
            }
            DEVICECONFIG.put(mp.get("deviceId").toString(),mp);
        }
    }
    public static Map<String, Object> getValue(String key) {

        if (Objects.isNull(key) || Objects.isNull(DEVICECONFIG.get(key))) {
            throw new RuntimeException("Key not present in Device Config file");
        }
        return DEVICECONFIG.get(key);

    }

}
