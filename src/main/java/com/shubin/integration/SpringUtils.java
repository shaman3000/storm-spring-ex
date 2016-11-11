package com.shubin.integration;

import org.springframework.context.support.AbstractApplicationContext;

/**
 * Created by sshubin on 10.11.2016.
 */

public class SpringUtils {

    public static Object initializeStormComponent(AbstractApplicationContext springContext, Object bean, String beanName) {
        if (springContext == null || bean == null)
            throw new IllegalStateException();
        Object wrappedBean = bean;
        springContext.getBeanFactory().autowireBean(wrappedBean);
        wrappedBean = springContext.getBeanFactory().initializeBean(wrappedBean, beanName);
        return wrappedBean;
    }

}
