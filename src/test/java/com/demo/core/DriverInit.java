package com.demo.core;

import com.demo.drivers.DriverInstance;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.appium.java_client.service.local.AppiumDriverLocalService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class DriverInit {

    private static AppiumDriver driver;
    private static AppiumDriverLocalService service;

    public static void setUp(String platform){
/*
        *//**
        //TODO SetUp

        Map<String, Object> deviceConfigs = DeviceSetUp.getValue("emulator-5554");
        File file = new File(FrameworkConstants.TEST_RESOURCE_PATH+deviceConfigs.get("app").toString());
        if(!file.exists()){
            throw new RuntimeException("App Does not exists in the given path");
        }
     /*  service = AppiumDriverLocalService.buildDefaultService();
        if(Objects.nonNull(service)){
            service.stop();
        }
        service.start();*//*
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

            //Appium 2
           // driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options );

            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), options );
            //driver = new AndroidDriver(service, options);

        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        return  driver;*/

        DriverInstance.initDriver("android");

    }


    public static void startVideoRecording(){
        ((CanRecordScreen) DriverInstance.getDriver()).startRecordingScreen();
    }

    public static void stopVideoRecording() {
        String base64String = ((CanRecordScreen) DriverInstance.getDriver()).stopRecordingScreen();
        byte[] data = Base64.getDecoder().decode(base64String);
        String destinationPath = "target/filename.mp4";
        Path path = Paths.get(destinationPath);
        try {
            Files.write(path, data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
