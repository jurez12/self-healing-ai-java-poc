package com.example.selfhealing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.*;

public class AISuggester {
	public static class Suggestion {
		public String locator;
		public double confidence;
		public String reason;
	}

	public List<Suggestion> suggest(String logicalName, String pageHtml) {
		List<Suggestion> out = new ArrayList<>();
		System.out.println("Suggestion flow");
		try {
			Document doc = Jsoup.parse(pageHtml);
			String[] tokens = logicalName.toLowerCase().split("\\W+");
			Elements elems = doc.select("input,button,a");
			Map<String, Double> scored = new LinkedHashMap<>();
			for (Element el : elems) {
				double score = 0.0;
				String id = el.id();
				String name = el.attr("name");
				String cls = el.className();
				String text = el.text();
				if (id != null && !id.isEmpty())
					score += 0.5;
				if (name != null && !name.isEmpty())
					score += 0.3;
				if (cls != null && !cls.isEmpty())
					score += 0.2;
				String combined = (id + " " + name + " " + cls + " " + text).toLowerCase();
				for (String t : tokens)
					if (t.length() > 0 && combined.contains(t))
						score += 0.25;
				if (score > 0.2) {
					String locator = buildBestLocator(el);
					double conf = Math.min(1.0, score);
					scored.put(locator, conf);
				}
			}
			scored.entrySet().stream().sorted((a, b) -> Double.compare(b.getValue(), a.getValue())).limit(3)
					.forEach(e -> {
						Suggestion s = new Suggestion();
						s.locator = e.getKey();
						s.confidence = e.getValue();
						s.reason = "heuristic-suggest";
						out.add(s);
					});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return out;
	}

	private String buildBestLocator(Element el) {
		if (el.hasAttr("id") && !el.id().isEmpty())
			return "By.id:" + el.id();
		if (el.hasAttr("name") && !el.attr("name").isEmpty())
			return "By.name:" + el.attr("name");
		if (el.hasAttr("class") && !el.className().isEmpty()) {
			String cls = el.className().trim().split("\\s+")[0];
			return "By.cssSelector:." + cls;
		}
		System.out.println("Best xpath AI: " + generateSimpleXPath(el));
		return "By.xpath::" + generateSimpleXPath(el);
	}

	private String generateSimpleXPath(Element el) {
		List<String> parts = new ArrayList<>();
		Element cur = el;
		while (cur != null && !cur.tagName().equals("html")) {
			Element parent = cur.parent();
			int idx = 1;
			if (parent != null) {
				for (Element sib : parent.children()) {
					if (sib.tagName().equals(cur.tagName())) {
						if (sib == cur)
							break;
						else
							idx++;
					}
				}
			}
			parts.add(0, String.format("/%s[%d]", cur.tagName(), idx));
			cur = parent;
		}
		return parts.isEmpty() ? "//" + el.tagName() : String.join("", parts);
	}
}
