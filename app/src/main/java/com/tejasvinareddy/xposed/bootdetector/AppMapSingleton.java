package com.tejasvinareddy.xposed.bootdetector;

import com.tejasvinareddy.xposed.bootdetector.model.AppWrapper;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class AppMapSingleton {

    // Single instance of this class
    private static AppMapSingleton instance = null;

    // Use of hashmap to improve time efficiency when a new app is detected in
    // hook.AppProducer.java
    private ConcurrentMap<String, AppWrapper> appMap;

    // Required private constructor
    private AppMapSingleton() {
        // Note the use of a concurrent hash map, as multiple threads will
        // have access to this single instance!
        appMap = new ConcurrentHashMap<>();
    }

    // FIXME switch over to a Mutually Exclusive Semaphore
    public static synchronized AppMapSingleton newInstance() {
        // Singleton implementation
        if (instance == null) {
            instance = new AppMapSingleton();
        }

        return instance;
    }

    public ConcurrentMap<String, AppWrapper> getAppMap() {
        return appMap;
    }
}
