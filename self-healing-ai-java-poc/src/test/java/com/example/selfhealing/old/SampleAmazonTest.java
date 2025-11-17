package com.example.selfhealing.old;

import com.example.selfhealing.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class SampleAmazonTest extends BaseTest {

    @Test
    public void searchAndOpenFirst() throws InterruptedException {
    	String site = "https://www.itcinfotech.com";
        driver.get(site);
        try { Thread.sleep(1000); } catch (InterruptedException ignored){}

        WebElement search = healer.findElement("searchBox", 5, site);
        search.clear();
        search.sendKeys("headphones");

        WebElement btn = healer.findElement("searchButton", 5, site);
        btn.click();

        try { Thread.sleep(3000); } catch (InterruptedException ignored){}

        WebElement first = healer.findElement("firstResultTitle", 5, site);
        System.out.println("First result text: " + first.getText());
        first.click();
    }
}
