package com.shubin.api.impl;

import org.apache.storm.topology.base.BaseComponent;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Created by sshubin on 14.11.2016.
 */

public class StormComponentProcessor implements BeanPostProcessor {

    private StormAspectInjector stormAspectInjector;

    public StormComponentProcessor() {

    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof BaseComponent) {
            ProxyFactory factory = new ProxyFactory(bean);
            if (stormAspectInjector != null)
                stormAspectInjector.injectAspects(bean, factory);
            factory.setProxyTargetClass(true);
            return factory.getProxy();
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        return bean;
    }

    public StormAspectInjector getStormAspectInjector() {
        return stormAspectInjector;
    }

    public void setStormAspectInjector(StormAspectInjector stormAspectInjector) {
        this.stormAspectInjector = stormAspectInjector;
    }


}
