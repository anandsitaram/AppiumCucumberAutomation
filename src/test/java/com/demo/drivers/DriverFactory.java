package com.demo.drivers;

import io.appium.java_client.AppiumDriver;

public final class DriverFactory {

    private static MobileDriverManager driverManager;

    private DriverFactory() {

    }

    public static void initDriver(String platform) {

        switch (platform.toUpperCase()) {
            case "ANDROID":
                driverManager = new AndroidDriverManager();
                break;
            case "IOS":
                throw new RuntimeException("Not Implemented");
            default:
                throw new RuntimeException("Platform is not specified");
        }
        driverManager.createDriver();


    }

    public static void quitDriver() {
        driverManager.quitDriver();
    }

    public static AppiumDriver getDriver() {
        return driverManager.getDriver();
    }
}
