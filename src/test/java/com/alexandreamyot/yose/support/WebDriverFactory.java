package com.alexandreamyot.yose.support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {

    public static WebDriver build() {
        WebDriver webDriver = new FirefoxDriver();
        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {
                    try {
                        webDriver.quit();
                    } catch (Throwable ignore) {}
                })
        );
        return webDriver;
    }

}
