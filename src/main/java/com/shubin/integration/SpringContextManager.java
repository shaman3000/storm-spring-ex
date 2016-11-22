package com.shubin.integration;

import org.apache.storm.task.TopologyContext;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * Created by sshubin on 10.11.2016.
 */

public class SpringContextManager {

    public static final String SPRING_CONTEXT_MANAGER = "SPRING_CONTEXT_MANAGER";

    // TODO: May be, it must be  Map<TopologyContext, AbstractApplicationContext>???
    private volatile AbstractApplicationContext workerApplicationContext;

    static {
        WorkerNodeContext.getInstance().put(SPRING_CONTEXT_MANAGER, new SpringContextManager());
    }

    public static SpringContextManager getInstance() {
        return WorkerNodeContext.getInstance().get(SPRING_CONTEXT_MANAGER);
    }

    public AbstractApplicationContext getApplicationContext(Map conf, TopologyContext context) {
        if (workerApplicationContext == null)
            synchronized (this) {
                if (workerApplicationContext == null) {
                    long start = System.currentTimeMillis();
                    workerApplicationContext = new ClassPathXmlApplicationContext("services.xml");
                    LoggerFactory.getLogger(SpringContextManager.class).info("Spring context initialized in " + (System.currentTimeMillis() - start) + "ms.");
                }
            }
        return workerApplicationContext;
    }

}
