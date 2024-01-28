package com.demo.drivers;

import com.demo.constants.FrameworkConstants;
import com.demo.core.DeviceSetUp;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Map;

public class AndroidDriverManager extends MobileDriverManager {

    protected AndroidDriverManager() {

    }

    protected void createDriver() {
        Map<String, Object> deviceConfigs = DeviceSetUp.getValue("emulator-5554");
        File file = new File(FrameworkConstants.TEST_RESOURCE_PATH+deviceConfigs.get("app").toString());
        if(!file.exists()){
            throw new RuntimeException("App Does not exists in the given path");
        }
        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName(deviceConfigs.get("deviceId").toString())
                .setAppPackage(deviceConfigs.get("appPackage").toString())
                .setAppActivity(deviceConfigs.get("appActivity").toString())
                .setAutomationName(deviceConfigs.get("automationName").toString())
                .setApp(file.getAbsolutePath())
                .setAvdLaunchTimeout(Duration.ofSeconds(30))
                .setAppWaitDuration(Duration.ofSeconds(30))
                .setNoReset((Boolean) deviceConfigs.get("noReset"))
                .setFullReset((Boolean) deviceConfigs.get("fullReset"));
        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), options );
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        DriverThLocal.setDriver(driver);
    }


}
