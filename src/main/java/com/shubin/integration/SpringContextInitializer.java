package com.shubin.integration;

import org.apache.storm.hooks.BaseWorkerHook;
import org.apache.storm.task.WorkerTopologyContext;

import java.util.Map;

/**
 * Created by sshubin on 10.11.2016.
 */

public class SpringContextInitializer extends BaseWorkerHook {

    public static final String SPRING_CONFIGURATION_FILE = "spring.configuration.file";

    private static final SpringContextInitializer defaultInitializerInstance = new SpringContextInitializer();

    protected String resolveSpringConfigurationFile(Map stormConf) {
        if (!stormConf.containsKey(SPRING_CONFIGURATION_FILE))
            throw new RuntimeException("Parameter [" + SPRING_CONFIGURATION_FILE + "] not found in worker config file.");
        return (String) stormConf.get(SPRING_CONFIGURATION_FILE);
    }

    @Override
    public void start(Map stormConf, WorkerTopologyContext context) {
        try {
            SpringContextManager.getInstance().startup(resolveSpringConfigurationFile(stormConf));
        } catch (Exception e) {
            throw new RuntimeException("Spring Context initialization error", e);
        }
    }

    @Override
    public void shutdown() {
        try {
            SpringContextManager.getInstance().shutdown();
        } catch (Exception e) {
            throw new RuntimeException("Spring Context shutdown error", e);
        }
    }

    public static SpringContextInitializer getDefaultInitializer() {
        return defaultInitializerInstance;
    }

}
