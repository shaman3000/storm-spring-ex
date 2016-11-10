package com.shubin.integration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by sshubin on 10.11.2016.
 */

public class WorkerNodeContext {

    private static final WorkerNodeContext instance = new WorkerNodeContext();

    private Map<String, Object> contextObjects = new ConcurrentHashMap<String, Object>();

    public void put(String key, Object value) {
        contextObjects.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) contextObjects.get(key);
    }

    public static WorkerNodeContext getInstance() {
        return instance;
    }

}
