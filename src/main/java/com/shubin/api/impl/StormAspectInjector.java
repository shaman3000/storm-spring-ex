package com.shubin.api.impl;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;

import java.util.Collections;
import java.util.List;

/**
 * Created by sshubin on 14.11.2016.
 */

public class StormAspectInjector {

    private List<AbstractAspect> aspectList = Collections.emptyList();

    public StormAspectInjector() {

    }

    public void injectAspects(Object bean, ProxyFactory factory) throws BeansException {
        for (AbstractAspect aspect : aspectList)
            aspect.apply(bean, factory);
    }

    public List<AbstractAspect> getAspectList() {
        return aspectList;
    }

    public void setAspectList(List<AbstractAspect> aspectList) {
        this.aspectList = aspectList;
    }

}

