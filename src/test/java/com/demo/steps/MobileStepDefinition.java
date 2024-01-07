package com.demo.steps;

import com.demo.core.DeviceSetUp;
import com.demo.core.DriverInit;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.List;

public class MobileStepDefinition {

    private  AppiumDriver driver;
    @BeforeAll
    public static void setUpDevice(){
        System.out.println("Before Setup");
        DeviceSetUp.initDeviceConfig();
    }
    @Given("I initialize configuration for {string}")
    public void i_initialize_configuration_for(String platform) {
         driver = DriverInit.setUp(platform);
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
            driver.findElement(AppiumBy.accessibilityId("open menu")).click();
            //Thread.sleep(20000);
            driver.findElement(AppiumBy.accessibilityId("menu item log in")).click();
            driver.findElement(AppiumBy.accessibilityId("Username input field")).sendKeys("bob@example.com");
            driver.findElement(AppiumBy.accessibilityId("Password input field")).sendKeys("10203040");
            driver.findElement(AppiumBy.accessibilityId("Login button")).click();
            System.out.println("Logged in");
            driver.findElement(AppiumBy.accessibilityId("cart badge")).click();
            List<WebElement> lst = driver.findElements(AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"product row\"]"));
            System.out.println("Size is " + lst.size());
            if (lst.size() > 0) {
                driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Remove Item\"]")).click();
                System.out.println("Item Removed");
            }
            driver.navigate().back();
            clickElement(driver, driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"store item text\" and @text=\"Sauce Labs Bike Light\"]")));
            Thread.sleep(2000);
            System.out.println(driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"product price\"]")).getText());

            driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"Add To Cart button\"]")).click();
            driver.findElement(AppiumBy.accessibilityId("cart badge")).click();
            List<WebElement> lst1 = driver.findElements(AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"product row\"]"));
            System.out.println("Size is " + lst1.size());

            driver.findElement(AppiumBy.accessibilityId("Proceed To Checkout button")).click();
             Thread.sleep(2000);

            //TODO Checkout
            driver.findElement(AppiumBy.accessibilityId("Full Name* input field")).sendKeys("Testing");
            driver.findElement(AppiumBy.accessibilityId("Address Line 1* input field")).sendKeys("Address 1");
            driver.findElement(AppiumBy.accessibilityId("City* input field")).sendKeys("Mysore");
            driver.findElement(AppiumBy.accessibilityId("State/Region input field")).sendKeys("Karnataka");
            driver.findElement(AppiumBy.accessibilityId("Zip Code* input field")).sendKeys("571401");
            driver.findElement(AppiumBy.accessibilityId("City* input field")).sendKeys("Mysore");
            driver.findElement(AppiumBy.accessibilityId("Country* input field")).sendKeys("India");
            driver.findElement(AppiumBy.accessibilityId("To Payment button")).click();
            Thread.sleep(2000);
            driver.findElement(AppiumBy.accessibilityId("Full Name* input field")).sendKeys("Testing");
            driver.findElement(AppiumBy.accessibilityId("Card Number* input field")).sendKeys("1234 5678 8888 8888");
            driver.findElement(AppiumBy.accessibilityId("Expiration Date* input field")).sendKeys("03/25");
            driver.findElement(AppiumBy.accessibilityId("Security Code* input field")).sendKeys("123");
            driver.findElement(AppiumBy.accessibilityId("Review Order button")).click();
            driver.findElement(AppiumBy.accessibilityId("Review Order button")).click();
            Thread.sleep(2000);

            driver.findElement(AppiumBy.accessibilityId("Place Order button")).click();
            Thread.sleep(2000);
            System.out.println(driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Thank you for your order\"]")).isDisplayed());
            System.out.println(driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Your new swag is on its way\"]")).isDisplayed());

            /*clickElement(driver,elementTap);
            Point loc = elementTap.getLocation();
            Dimension dim = elementTap.getSize();

            Point centreOfEle = getCentreOfElement(loc, dim);
            PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
            Sequence sequence = new Sequence(finger1, 1).addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centreOfEle))
                    .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                    .addAction(new Pause(finger1, Duration.ofMillis(500)))
                    .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            driver.perform(Collections.singletonList(sequence));
            System.out.println("Anand");*/


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    @AfterAll
    public static void tearDown(){
        DriverInit.tearDown();
    }

    private Point getCentreOfElement(Point loc, Dimension dim) {
        return new Point(loc.getX() + dim.getWidth() / 2, loc.getX() + dim.getHeight() / 2);
    }

    public void clickElement(AppiumDriver driver, WebElement element) {
        driver.executeScript("mobile: clickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId()
        ));
    }

    public void swipe(AppiumDriver driver, WebElement element) {
        driver.executeScript("mobile: clickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId()
        ));
    }
}
