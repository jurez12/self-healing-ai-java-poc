package com.example.selfhealing;

import java.util.regex.Matcher;
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
		Client client = Client.builder().apiKey("AIzaSyA73wgbQ0cyou6cPbRw3LwwVa2Nr202D30").build();
		String value = "From the webpage at " + site + ", identify" + " the XPath selector that uniquely points to the "
				+ logicalName + " of the site. Provide only the exact XPath expression of the " + logicalName
				+ " element without any additional text, explanation, or formatting.";
		Content userContent = Content.fromParts(Part.fromText(value));
		GenerateContentResponse response = client.models.generateContent("gemini-2.5-flash", userContent, null);
		System.out.println("Google-Gemini-2.5-flash Suggestion" + response.text());
		return response.text();
	}

}