package com.example.selfhealing;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
	protected WebDriver driver;
	protected SelfHealingService healer;

	@BeforeClass
	public void setup() {
		driver = DriverFactory.getDriver();
		driver.manage().window().maximize();
		healer = new SelfHealingService(driver);
	}

	@AfterClass
	public void tearDown() {
		DriverFactory.quitDriver();
	}
}
