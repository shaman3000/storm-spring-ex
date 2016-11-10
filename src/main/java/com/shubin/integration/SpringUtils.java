package com.shubin.integration;

import org.springframework.context.support.AbstractApplicationContext;

/**
 * Created by sshubin on 10.11.2016.
 */

public class SpringUtils {


    public static Object initializeStormComponent(Object bean, String beanName) throws IllegalStateException {
        AbstractApplicationContext spring = SpringContextManager.getInstance().getApplicationContext();
        if (spring == null)
            throw new IllegalStateException("Spring Context is not initialized.");
        Object wrappedBean = bean;
        spring.getBeanFactory().autowireBean(wrappedBean);
        wrappedBean = spring.getBeanFactory().initializeBean(wrappedBean, beanName);
        return wrappedBean;
    }

}
