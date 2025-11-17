package com.example.selfhealing.old;

import com.example.selfhealing.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test
    public void verifyAmazonHomePageElements() {
    	String site = "https://www.itcinfotech.com";
        driver.get(site);
/*
        WebElement logo = healer.findElement("amazonLogo", 5);
        Assert.assertTrue(logo.isDisplayed(), "Amazon logo should be visible");

        WebElement search = healer.findElement("searchBox", 5);
        Assert.assertTrue(search.isDisplayed(), "Search box should be visible");
*/
        WebElement account = healer.findElement("accountListLink", 5, site);
        Assert.assertTrue(account.isDisplayed(), "Account link should be visible");
        System.out.println("Account link text: " + account.getText());
    }
}
