package com.shubin.api.impl;

import org.springframework.aop.framework.ProxyFactory;

/**
 * Created by sshubin on 14.11.2016.
 */

public abstract class AbstractAspect {

    public abstract void apply(Object bean, ProxyFactory factory);

}
