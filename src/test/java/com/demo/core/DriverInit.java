package com.demo.core;

import com.demo.constants.FrameworkConstants;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Map;
import java.util.Objects;

public class DriverInit {

    private static AppiumDriver driver;
    private static AppiumDriverLocalService service;

    public static AppiumDriver setUp(String platform){

        //TODO SetUp

        Map<String, Object> deviceConfigs = DeviceSetUp.getValue("emulator-5554");
        File file = new File(FrameworkConstants.TEST_RESOURCE_PATH+deviceConfigs.get("app").toString());
        if(!file.exists()){
            throw new RuntimeException("App Does not exists in the given path");
        }
        service = AppiumDriverLocalService.buildDefaultService();
        if(Objects.nonNull(service)){
            service.stop();
        }
        service.start();
        UiAutomator2Options options = new UiAutomator2Options()
               // .setDeviceName("pixel_6_pro")
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
            driver = new AndroidDriver(
                    new URL("http://127.0.0.1:4723"), options
            );
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return  driver;
      //  ((CanRecordScreen) driver).startRecordingScreen();
    }


    public static void tearDown(){

        service.stop();
    }
}
