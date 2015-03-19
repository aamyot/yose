package com.alexandreamyot.yose.support;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Browser {

    private static WebDriver webDriver = WebDriverFactory.build();

    public static void navigateTo(String url) {
        webDriver.navigate().to(url);
    }

    public static WebElement element(By selector) {
        return webDriver.findElement(selector);
    }


    public static WebElement input(By selector, String text) {
        WebElement element = element(selector);
        element.clear();
        element.sendKeys(text);
        return element;
    }

    public static void execute(String js) {
        if (webDriver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) webDriver).executeScript(js);
        } else {
            throw new UnsupportedOperationException("The Selenium driver doesn't not support javascript execution");
        }
    }

    public static void click(By selector) {
        element(selector).click();
    }
}
