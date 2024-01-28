package com.demo.drivers;

import io.appium.java_client.AppiumDriver;

import java.util.Objects;

public final class DriverInstance {

    private DriverInstance() {

    }

    public static void initDriver(String platform) {

        if (Objects.isNull(DriverThLocal.getDriver())) {
            DriverFactory.initDriver(platform);
        }
    }

    public static AppiumDriver getDriver() {
        return DriverFactory.getDriver();
    }

    public static void quitDriver() {
        DriverFactory.quitDriver();

    }

}