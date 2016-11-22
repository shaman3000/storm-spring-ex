package com.shubin.integration;

import org.springframework.context.support.AbstractApplicationContext;

/**
 * Created by sshubin on 10.11.2016.
 */

public class SpringUtils {

    public static <T> T initializeStormComponent(AbstractApplicationContext springContext, T bean, String beanName) throws Exception {
        if (springContext == null || bean == null)
            throw new IllegalStateException();
        T wrappedBean = bean;
        springContext.getBeanFactory().autowireBean(wrappedBean);
        wrappedBean = (T) springContext.getBeanFactory().initializeBean(wrappedBean, beanName);
        return wrappedBean;
    }

}
