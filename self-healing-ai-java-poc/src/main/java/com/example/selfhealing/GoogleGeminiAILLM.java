package com.example.selfhealing;

import java.util.regex.Matcher;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Pattern;

import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Part;

public class GoogleGeminiAILLM {
	public static synchronized String aiGetXpath(String logicalName, String site) {
		for (int i = 0; i < 10; i++) {
			try {
				return getElementFromLLM(logicalName, site);
			} catch (Exception e) {
				System.out.println("Failed  " + e.getMessage());
			}
		}
		return null;
	}

	private static String getElementFromLLM(String logicalName, String site) {
		Properties prop = new Properties();
		try (InputStream input = new FileInputStream("config.properties")) {
			prop.load(input);
			// Read properties by key
			String key = prop.getProperty("key");
			String token1 = prop.getProperty("token1");
			String token2 = prop.getProperty("token2");
			String token3 = prop.getProperty("token3");
			String token4 = prop.getProperty("token4");
			String token5 = prop.getProperty("token5");

			Client client = Client.builder().apiKey(key).build();
			String value = token1 + site + token2 + token3 + logicalName + token4 + logicalName + token5;
			Content userContent = Content.fromParts(Part.fromText(value));
			GenerateContentResponse response = client.models.generateContent("gemini-2.5-flash", userContent, null);
			System.out.println("Google-Gemini-2.5-flash Suggestion" + response.text());

			return response.text();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return "";
	}

}