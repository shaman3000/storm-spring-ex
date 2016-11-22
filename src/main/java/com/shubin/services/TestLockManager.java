package com.shubin.services;

/**
 * Created by sshubin on 21.11.2016.
 */

public class TestLockManager extends AbstractLockManager {

    public TestLockManager() {
        super();
    }

    @Override
    public void acquire(long timeout, String... key) {
        getLog().info("acquire");
    }

    @Override
    public boolean acquireIfAbsent(long timeout, String... key) {
        getLog().info("acquireIfAbsent");
        return true;
    }

    @Override
    public void prolong(long timeout, String... key) {
        getLog().info("prolong");
    }

    @Override
    public void release(String... key) {
        getLog().info("release");
    }

}
