package com.example.selfhealing;

import org.openqa.selenium.By;
import java.util.*;

public class LocatorRepository {
    private static Map<String, List<By>> repo = new HashMap<>();

    static {
        // Amazon search box/button (logical names)
        repo.put("searchBox", Arrays.asList(
            By.id("twotabsearchtextbox"),
            By.cssSelector("input[name='field-keywords']"),
            By.xpath("//input[contains(@placeholder,'Search') or contains(@aria-label,'Search')]")
        ));

        repo.put("searchButton", Arrays.asList(
            By.id("nav-search-submit-button"),
            By.cssSelector("input[type='submit'][value='Go']"),
            By.xpath("//input[@type='submit' or @type='button' or contains(@aria-label,'search')]")
        ));

        repo.put("firstResultTitle", Arrays.asList(
            By.cssSelector("div.s-main-slot div[data-component-type='s-search-result'] h2 a"),
            By.xpath("(//div[contains(@data-component-type,'s-search-result')]//h2//a)[1]"),
            By.cssSelector(".s-result-item h2 a")
        ));

        // Home page logo and account link
        repo.put("amazonLogo", Arrays.asList(
            By.id("nav-logo-spritesAbc"),  // nav-logo-sprites
            By.cssSelector("a.nav-logo-link"),
            By.xpath("//a[contains(@aria-label,'Amazonabc')]") // //a[contains(@aria-label,'Amazon')]
        ));

        repo.put("accountListLink", Arrays.asList(
            By.id("nav-link-accountList"), //nav-link-accountList
            By.cssSelector("#nav-tools a[href*='signin_']"), // #nav-tools a[href*='signin']
            By.xpath("//a[contains(@href,'signin_ABC')]") // //a[contains(@href,'signin')]
        ));

        // Login page username/email field and continue button
        repo.put("loginEmail", Arrays.asList(
            By.id("ap_email"),
            By.name("email"),
            By.xpath("//input[contains(@id,'email') or contains(@name,'email')]")
        ));

        repo.put("continueButton", Arrays.asList(
            By.id("continue"),
            By.cssSelector("input#continue"),
            By.xpath("//input[@type='submit' and (contains(@aria-labelledby,'continue') or @id='continue')]")
        ));
        
        repo.put("logo", Arrays.asList(
                By.id("continue-"),
                By.cssSelector("fixed-logo-"),
                By.xpath("//img[@alt='ITC Infotech'][1]-")
            ));
        
        repo.put("Get In Touch", Arrays.asList(
                By.id("Get-"),
                By.cssSelector("fixed-logo-"),
                By.xpath("//a[contains(@href,'getintouch-')]")
            ));
        
        repo.put("Full Name Textbox", Arrays.asList(
                By.id("Wrong"),
                By.cssSelector("Wrong-"),
                By.xpath("//input[@placeholder='Full Name']")
            ));
        
        repo.put("Careers", Arrays.asList(
                By.id("Get-"),
                By.cssSelector("careers-"),
                By.xpath("//a[contains(@href,'careerssss-')]")
            ));
    }

    public static List<By> candidates(String logicalName) {
        return repo.getOrDefault(logicalName, Collections.emptyList());
    }
}
