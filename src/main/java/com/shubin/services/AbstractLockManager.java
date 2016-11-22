package com.shubin.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * Created by sshubin on 21.11.2016.
 */

abstract class AbstractLockManager implements LockManager, Serializable {

    private String loggerName;

    private transient Logger log;

    public AbstractLockManager() {
        resetLoggerObject();
    }

    protected void resetLoggerObject() {
        if (loggerName == null)
            log = LoggerFactory.getLogger(getClass().getName());
        else
            log = LoggerFactory.getLogger(loggerName);
    }

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
        resetLoggerObject();
    }

    protected Logger getLog() {
        return log;
    }

    @Override
    public abstract void acquire(long timeout, String... key);

    @Override
    public abstract boolean acquireIfAbsent(long timeout, String... key);

    @Override
    public abstract void prolong(long timeout, String... key);

    @Override
    public abstract void release(String... key);

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        resetLoggerObject();
    }


}
