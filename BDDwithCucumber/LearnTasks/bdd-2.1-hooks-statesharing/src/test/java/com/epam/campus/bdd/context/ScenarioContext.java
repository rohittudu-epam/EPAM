package com.epam.campus.bdd.context;

import java.util.HashMap;
import java.util.Map;

/**
 * ScenarioContext provides a shared key-value store for passing data between
 * Cucumber step definitions within a single scenario. PicoContainer creates
 * a new instance per scenario, ensuring isolation between tests.
 */
public class ScenarioContext {

    private final Map<String, Object> context = new HashMap<>();

    public void set(String key, Object value) {
        context.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key, Class<T> type) {
        return (T) context.get(key);
    }

    public Object get(String key) {
        return context.get(key);
    }

    public boolean containsKey(String key) {
        return context.containsKey(key);
    }

    public void clear() {
        context.clear();
    }
}
