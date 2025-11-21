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
				System.out.println("JSON locator :" + byStr);
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
		
		// 2. repo candidates, get local, if present save to json and return
		List<By> candidates = LocatorRepository.candidates(logicalName);
		for (By c : candidates) {
			WebElement e = tryFind(c, timeoutSeconds);
			if (e != null) {
				System.out.println("Picked From Local Repo: " +c.toString() + " Logical Name" + logicalName);
				HealedLocatorsStore.save(logicalName, c.toString(), 0.9, "repo-candidate", "repo");
				return e;
			}
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
					System.out.println("[AI-HEAL(LLM(GEMINI2.5)] " + logicalName + " -> " + xpath + " (conf " + 0.80 + ")");
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

}
