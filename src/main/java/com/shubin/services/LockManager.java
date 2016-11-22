package com.shubin.services;

public interface LockManager {

        public void acquire(long timeout, String... key);

        public boolean acquireIfAbsent(long timeout, String... key);

        public void prolong(long timeout, String... key);

        public void release(String... key);

    }
