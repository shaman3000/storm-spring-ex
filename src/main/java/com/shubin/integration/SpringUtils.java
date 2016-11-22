package com.shubin.integration;

import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * Created by sshubin on 10.11.2016.
 */

public class SpringUtils {

    private static <T> T getTargetObject(Object proxy) throws Exception {
        while((AopUtils.isJdkDynamicProxy(proxy)))
            return (T) getTargetObject(((Advised)proxy).getTargetSource().getTarget());
        return (T) proxy;
    }

    public static <T> T initializeStormComponent(AbstractApplicationContext springContext, T bean, String beanName) throws Exception {
        if (springContext == null || bean == null)
            throw new IllegalStateException();
        T wrappedBean = bean;
        springContext.getBeanFactory().autowireBean(wrappedBean);
        wrappedBean = getTargetObject(springContext.getBeanFactory().initializeBean(wrappedBean, beanName));
        return wrappedBean;
    }

}
