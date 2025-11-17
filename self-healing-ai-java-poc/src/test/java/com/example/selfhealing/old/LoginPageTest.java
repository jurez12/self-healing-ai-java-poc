package com.example.selfhealing.old;

import com.example.selfhealing.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @Test
    public void verifyLoginPageFields() {
    	String site = "https://www.itcinfotech.com";
        driver.get(site);

        WebElement email = healer.findElement("loginEmail", 5, site);
        Assert.assertTrue(email.isDisplayed(), "Email field should be displayed");
        email.sendKeys("test@example.com");

        WebElement cont = healer.findElement("continueButton", 5, site);
        Assert.assertTrue(cont.isDisplayed(), "Continue button should be displayed");
        cont.click();

        System.out.println("Clicked Continue button successfully.");
    }
}
