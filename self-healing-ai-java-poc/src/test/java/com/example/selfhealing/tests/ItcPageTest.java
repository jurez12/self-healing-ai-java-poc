package com.example.selfhealing.tests;

import com.example.selfhealing.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ItcPageTest extends BaseTest {
	public static String site = "https://www.itcinfotech.com";

	@Test
	@Story("Verify Get In Touch and Full Name elements")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Checks visibility and functionality of 'Get In Touch' and 'Full Name' elements on ITC page")
	public void verifyITCPageElements() {
		try {
			driver.get(site);
			System.out.println("Accept Cookie");
			if (driver.findElement(By.xpath("//a[contains(.,'Accept All')]")).isDisplayed()) {
				driver.findElement(By.xpath("//a[contains(.,'Accept All')]")).click();
			}

			WebElement getInTouch = healer.findElement("Get In Touch", 5, driver.getCurrentUrl());
			Assert.assertTrue(getInTouch.isDisplayed(), "Get In Touch displayed");
			System.out.println("getInTouch Text" + getInTouch.getText());
			getInTouch.click();

			WebElement fullName = healer.findElement("Full Name Textbox", 5, driver.getCurrentUrl());
			Assert.assertTrue(fullName.isDisplayed(), "Full Name displayed");
		} catch (Exception e) {
			System.out.println("Done, Retry");

		}
	}
}
