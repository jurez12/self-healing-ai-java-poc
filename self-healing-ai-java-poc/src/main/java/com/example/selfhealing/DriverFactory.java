package com.example.selfhealing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
//    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	static WebDriver driver;

    public static WebDriver getDriver() {
    		WebDriverManager.chromedriver().clearDriverCache().clearResolutionCache();
        	WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            return driver;
    }

    public static void quitDriver() {
        if (driver!= null) {
            driver.quit();
        }
    }
}
