package com.shubin.api.impl;

import org.apache.storm.topology.base.BaseComponent;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by sshubin on 14.11.2016.
 */

@Component
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

    @Autowired
    public void setStormAspectInjector(StormAspectInjector stormAspectInjector) {
        this.stormAspectInjector = stormAspectInjector;
    }


}
