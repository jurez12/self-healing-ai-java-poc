package com.example.selfhealing.tests;

import com.example.selfhealing.BaseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ItcPageTest extends BaseTest {

    @Test
    public void verifyAmazonHomePageElements() {
    	String site = "https://www.itcinfotech.com";
        driver.get(site);
        /* Acccept Cookies */
        if(driver.findElement(By.xpath("//a[contains(.,'Accept All')]")).isDisplayed()) {
        	driver.findElement(By.xpath("//a[contains(.,'Accept All')]")).click();
        }
     
        WebElement getInTouch = healer.findElement("Get In Touch", 5, site);
        Assert.assertTrue(getInTouch.isDisplayed(), "Get In Touch displayed");
        System.out.println("getInTouch Text" + getInTouch.getText());
        
        WebElement career = healer.findElement("Careers", 5, site);
        Assert.assertTrue(getInTouch.isDisplayed(), "Career Displayed");
        System.out.println("Careers Text " + getInTouch.getText());
    }
}
