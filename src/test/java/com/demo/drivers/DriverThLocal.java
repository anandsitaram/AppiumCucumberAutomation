package com.demo.drivers;

import io.appium.java_client.AppiumDriver;

public final class DriverThLocal {
    private DriverThLocal() {

    }

    private static ThreadLocal<AppiumDriver> driverThread = new ThreadLocal<>();

    static AppiumDriver getDriver() {
        return driverThread.get();

    }

    static void setDriver(AppiumDriver driver) {

        driverThread.set(driver);
    }

    static void unload() {
        driverThread.remove();
    }
}