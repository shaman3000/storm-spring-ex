package com.shubin.integration;

import java.io.Serializable;

/**
 * Created by sshubin on 10.11.2016.
 */

public class SpringDelegatorHolder<C> implements Serializable {

    private Object defaultValue;

    private Object value;

    private boolean springInialized = false;

    public SpringDelegatorHolder(C value, C defaultValue) {
        this.value = value;
        this.defaultValue = defaultValue;
    }

    public C get() {
        Object ret = (value != null) ? value : defaultValue;
        return (C) ret;
    }

    public void springifyComponent(String beanName) {
        if (value != null && !springInialized) {
            value = SpringUtils.initializeStormComponent(value, beanName);
            springInialized = true;
        }
    }

}
