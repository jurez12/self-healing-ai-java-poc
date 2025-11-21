package com.example.selfhealing;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
	protected WebDriver driver;
	protected SelfHealingService healer;

	@BeforeMethod
	public void setup() {
		driver = DriverFactory.getDriver();
		driver.manage().window().maximize();
		healer = new SelfHealingService(driver);
	}

	@AfterMethod
	public void tearDown() {
		DriverFactory.quitDriver();
	}
}
