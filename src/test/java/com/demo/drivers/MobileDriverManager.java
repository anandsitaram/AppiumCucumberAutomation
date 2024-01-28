package com.demo.drivers;

import io.appium.java_client.AppiumDriver;

import java.util.Objects;

public abstract class MobileDriverManager {
    protected AppiumDriver driver;

    protected abstract void createDriver();

    protected AppiumDriver getDriver() {
        return DriverThLocal.getDriver();
    }

    protected void quitDriver() {
        if (Objects.nonNull(DriverThLocal.getDriver())) {
            getDriver().quit();
            DriverThLocal.unload();
        }
    }
}