package com.shubin.integration;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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

    public void startup(String springConfigFile) throws Exception {
        if (workerApplicationContext == null)
            synchronized (SpringContextManager.class) {
                if (workerApplicationContext == null)
                    workerApplicationContext = new ClassPathXmlApplicationContext(springConfigFile);
            }

    }

    public void shutdown() {
        if (workerApplicationContext != null)
            synchronized (SpringContextManager.class) {
                if (workerApplicationContext != null) {
                    workerApplicationContext.close();
                    workerApplicationContext = null;
                }
            }
    }

    public AbstractApplicationContext getApplicationContext() {
        return workerApplicationContext;
    }


}
