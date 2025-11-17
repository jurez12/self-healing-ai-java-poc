package com.example.selfhealing;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.time.Instant;
import java.util.*;

public class HealedLocatorsStore {
    private static final File STORE = new File("healed-locators.json");
    private static Map<String, Map<String,Object>> map = new HashMap<>();
    private static final Gson gson = new Gson();
    static {
        if (STORE.exists()) {
            try (Reader r = new FileReader(STORE)) {
                Type t = new TypeToken<Map<String, Map<String,Object>>>(){}.getType();
                map = gson.fromJson(r, t);
                if (map == null) map = new HashMap<>();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static synchronized void save(String logicalName, String locatorString, double confidence, String reason, String source) {
        Map<String,Object> meta = new HashMap<>();
        meta.put("locator", locatorString);
        meta.put("confidence", confidence);
        meta.put("reason", reason);
        meta.put("source", source);
        meta.put("createdAt", Instant.now().toString());
        meta.put("status", confidence >= 0.85 ? "AUTO" : "PENDING");
        map.put(logicalName, meta);
        persist();
    }

	public static synchronized Map<String, Object> get(String logicalName) {
		//System.out.println(" String from Healed locator store " +logicalName + " Value is " + map.get(logicalName) );
		return map.get(logicalName);
	}

    private static void persist() {
        try (Writer w = new FileWriter(STORE)) {
            gson.toJson(map, w);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
