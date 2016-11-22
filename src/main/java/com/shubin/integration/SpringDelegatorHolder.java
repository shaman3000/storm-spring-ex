package com.shubin.integration;

import org.apache.storm.task.TopologyContext;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by sshubin on 10.11.2016.
 */

public class SpringDelegatorHolder<C> implements Serializable {

    private Object defaultValue;

    private Object value;

    private transient boolean springInialized = false;

    public SpringDelegatorHolder(C value, C defaultValue) {
        this.value = value;
        this.defaultValue = defaultValue;
    }

    public C get() {
        Object ret = (value != null) ? value : defaultValue;
        return (C) ret;
    }

    public void springifyComponent(Map conf, TopologyContext context, String beanName) {
        if (value != null && !springInialized) {
            try {
                value = SpringUtils.initializeStormComponent(
                            SpringContextManager.getInstance().getApplicationContext(conf, context), value, beanName);
                springInialized = true;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void writeObject(java.io.ObjectOutputStream stream) throws IOException {
        if (!springInialized)
            stream.defaultWriteObject();
    }

}
