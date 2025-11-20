package com.example.selfhealing;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class SelfHealingService {
	private WebDriver driver;

	public SelfHealingService(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement findElement(String logicalName, long timeoutSeconds, String site) {
		try {
		// 1. try healed, Get from Json if found return;
		Map<String, Object> meta = HealedLocatorsStore.get(logicalName);
		if (meta != null) {
			try {
				String byStr = (String) meta.get("locator");
				System.out.println("json locator" + byStr);
				WebElement e = driver.findElement(By.xpath(byStr));
				if (e != null) {
					System.out.println("Picked From Json" +byStr);
					System.out.println(byStr + "FindElement");
					return e;
				} else {
					
				}
					
			} catch (Exception ignored) {
			}
		}
		}catch(Exception e) {
			System.out.println ("Error in reading JSON");
		}
		
		boolean found = false;
		// 2. repo candidates, get local, if present save to json and return
		List<By> candidates = LocatorRepository.candidates(logicalName);
		for (By c : candidates) {
			WebElement e = tryFind(c, timeoutSeconds);
			if (e != null) {
				found = true;
				System.out.println("Picked " +c.toString());
				HealedLocatorsStore.save(logicalName, c.toString(), 0.9, "repo-candidate", "repo");
				System.out.println("Locator " + logicalName + " found correctly" + found);
				return e;
			}
		}
		if (found == false) {
			System.out.println("Locator " + logicalName + " not found as mention in code " + found);
		} else {
			System.out.println("Locator " + logicalName + " found correctly" + found);
		}
		// 3. if not found both JSON And local, get using AI, have it as 0.8 confident 
		try {
			for (int i = 0; i < 5; i++) {
				try {
				String xpath = Google_Gemini_AI_LLM.aiGetXpath(logicalName, site);
				// By by = parseBy(xpath);
				WebElement e = driver.findElement(By.xpath(xpath));
				if (e != null) {
					HealedLocatorsStore.save(logicalName, xpath, 0.8, "healing", "Google-Gemini-2.5-flash");
					System.out.println("[AI-HEAL] " + logicalName + " -> " + xpath + " (conf " + 0.80 + ")");
					return e;
				} else {
					continue;
				}
				} catch(Exception e) {
					System.out.println("Excpetion " + e);
				}
			}
		} catch (Exception ex) {
			System.out.println("Excpetion " + ex);
		}
		throw new NoSuchElementException("Unable to find element in JSON/Repo/AI: " + logicalName);
		
	}

	private WebElement tryFind(By by, long timeoutSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
			return wait.until(d -> d.findElement(by));
		} catch (Exception e) {
			return null;
		}
	}

	private By parseBy(String s) {
		if (s.startsWith("By.id:"))
			return By.id(s.substring("By.id:".length()));
		if (s.startsWith("By.name:"))
			return By.name(s.substring("By.name:".length()));
		if (s.startsWith("By.cssSelector:"))
			return By.cssSelector(s.substring("By.cssSelector:".length()));
		if (s.startsWith("By.xpath:"))
			return By.xpath(s.substring("By.xpath:".length()));
		return By.cssSelector(s);
	}
}
