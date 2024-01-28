package com.demo.steps;

import com.demo.core.AppiumHelper;
import com.demo.core.DeviceSetUp;
import com.demo.core.DriverInit;
import com.demo.drivers.DriverInstance;
import com.demo.objects.MenuScreen;
import com.demo.utils.ConfigUtil;
import io.appium.java_client.AppiumBy;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MobileStepDefinition {


    private AppiumHelper appiumHelper;
    @BeforeAll
    public  static void setUpDevice(){
      /*  System.out.println("Before Setup");
        DeviceSetUp.initDeviceConfig();
        String device=ConfigUtil.getValue("device");
        DriverInit.setUp(device);*/
    }

    @Before
    public  void setUpApplication(){

        System.out.println("Before Setup");
        DeviceSetUp.initDeviceConfig();
        String device=ConfigUtil.getValue("device");
        DriverInit.setUp(device);

        DriverInit.startVideoRecording();

    }

    @After
    public  void closeApplication(){



        DriverInit.stopVideoRecording();
        DriverInstance.quitDriver();
    }
    @Given("I initialize configuration for {string}")
    public void i_initialize_configuration_for(String platform) {
       // driver = DriverInit.setUp(platform);
        System.out.println("I initialize configuration for "+platform);
    }
    @When("I launch Application")
    public void i_launch_application() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("i_launch_application");
    }
    @Then("I Do Checkout")
    public void i_login() {
        try {
            appiumHelper.clickElementByAccessibilityId(MenuScreen.menuClk);
            Thread.sleep(20000);
            DriverInstance.getDriver().findElement(AppiumBy.accessibilityId("menu item log in")).click();
            DriverInstance.getDriver().findElement(AppiumBy.accessibilityId("Username input field")).sendKeys("bob@example.com");
            DriverInstance.getDriver().findElement(AppiumBy.accessibilityId("Password input field")).sendKeys("10203040");
            DriverInstance.getDriver().findElement(AppiumBy.accessibilityId("Login button")).click();
            System.out.println("Logged in");
            DriverInstance.getDriver().findElement(AppiumBy.accessibilityId("cart badge")).click();
            List<WebElement> lst = DriverInstance.getDriver().findElements(AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"product row\"]"));
            System.out.println("Size is " + lst.size());
            if (lst.size() > 0) {
                DriverInstance.getDriver().findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Remove Item\"]")).click();
                System.out.println("Item Removed");
            }
            DriverInstance.getDriver().navigate().back();
            DriverInstance.getDriver().findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"store item text\" and @text=\"Sauce Labs Bike Light\"]")).click();
          //  clickElement(driver, DriverInstance.getDriver().findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"store item text\" and @text=\"Sauce Labs Bike Light\"]")));
            Thread.sleep(2000);
            System.out.println(DriverInstance.getDriver().findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"product price\"]")).getText());

            DriverInstance.getDriver().findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"Add To Cart button\"]")).click();
            DriverInstance.getDriver().findElement(AppiumBy.accessibilityId("cart badge")).click();
            List<WebElement> lst1 = DriverInstance.getDriver().findElements(AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"product row\"]"));
            System.out.println("Size is " + lst1.size());

            DriverInstance.getDriver().findElement(AppiumBy.accessibilityId("Proceed To Checkout button")).click();
             Thread.sleep(2000);

            //TODO Checkout
            DriverInstance.getDriver().findElement(AppiumBy.accessibilityId("Full Name* input field")).sendKeys("Testing");
            DriverInstance.getDriver().findElement(AppiumBy.accessibilityId("Address Line 1* input field")).sendKeys("Address 1");
            DriverInstance.getDriver().findElement(AppiumBy.accessibilityId("City* input field")).sendKeys("Mysore");
            DriverInstance.getDriver().findElement(AppiumBy.accessibilityId("State/Region input field")).sendKeys("Karnataka");
            DriverInstance.getDriver().findElement(AppiumBy.accessibilityId("Zip Code* input field")).sendKeys("571401");
            DriverInstance.getDriver().findElement(AppiumBy.accessibilityId("City* input field")).sendKeys("Mysore");
            DriverInstance.getDriver().findElement(AppiumBy.accessibilityId("Country* input field")).sendKeys("India");
            DriverInstance.getDriver().findElement(AppiumBy.accessibilityId("To Payment button")).click();
            Thread.sleep(2000);
            DriverInstance.getDriver().findElement(AppiumBy.accessibilityId("Full Name* input field")).sendKeys("Testing");
            DriverInstance.getDriver().findElement(AppiumBy.accessibilityId("Card Number* input field")).sendKeys("1234 5678 8888 8888");
            DriverInstance.getDriver().findElement(AppiumBy.accessibilityId("Expiration Date* input field")).sendKeys("03/25");
            DriverInstance.getDriver().findElement(AppiumBy.accessibilityId("Security Code* input field")).sendKeys("123");
            DriverInstance.getDriver().findElement(AppiumBy.accessibilityId("Review Order button")).click();
            DriverInstance.getDriver().findElement(AppiumBy.accessibilityId("Review Order button")).click();
            Thread.sleep(2000);

            DriverInstance.getDriver().findElement(AppiumBy.accessibilityId("Place Order button")).click();
            Thread.sleep(2000);
            System.out.println(DriverInstance.getDriver().findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Thank you for your order\"]")).isDisplayed());
            System.out.println(DriverInstance.getDriver().findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Your new swag is on its way\"]")).isDisplayed());


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    @AfterAll
    public static void tearDown(){

      //  DriverInstance.quitDriver();

    }

}
