package com.demo.core;

import com.demo.drivers.DriverInstance;
import io.appium.java_client.AppiumBy;

public class AppiumHelper {


    public static void clickElementByAccessibilityId(String id){
        DriverInstance.getDriver().findElement(AppiumBy.accessibilityId(id)).click();
    }
}
