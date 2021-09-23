package com.ls.validator.message;

import lombok.Getter;
import lombok.Setter;
import lombok.Synchronized;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

public final class Messages {

    private final ConcurrentHashMap<String, Map<String, String>> messageMap;

    private static final String DEFAULT = "default";

    @Getter
    @Setter
    private String defaultLanguage;

    public Messages(String propertiesName, String defaultLanguage, String[] languages) {
        messageMap = new ConcurrentHashMap<>();
        if (defaultLanguage != null && !"".equals(defaultLanguage.trim())) {
            this.defaultLanguage = defaultLanguage;
        }
        initResources(propertiesName, DEFAULT);
        for (String language : languages) {
            initResources(propertiesName + "_" + language, language);
        }
    }

    public String getMessage(String key, String language) {
        if (language == null || "".equals(language.trim())) {
            language = defaultLanguage;
        }
        Map<String, String> map = messageMap.get(key);
        if (map != null) {
            String str = map.get(language == null ? DEFAULT : language);
            if (str == null) {
                str = map.get(DEFAULT);
            }
            return str;
        }
        return null;
    }

    @Synchronized
    private void initResources(String propertiesName, String language) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(propertiesName);
        if (resourceBundle == null) {
            return;
        }
        Enumeration<String> keys = resourceBundle.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            String value = resourceBundle.getString(key);
            Map<String, String> map = messageMap.getOrDefault(key, new HashMap<>());
            map.put(language, value);
            messageMap.put(key, map);
        }
    }
}
