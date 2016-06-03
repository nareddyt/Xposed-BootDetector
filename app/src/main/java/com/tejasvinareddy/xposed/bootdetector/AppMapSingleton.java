package com.tejasvinareddy.xposed.bootdetector;

import com.tejasvinareddy.xposed.bootdetector.model.AppWrapper;

import java.util.HashMap;
import java.util.Map;

public class AppMapSingleton {

    // Single instance of this class
    private static AppMapSingleton instance;

    // Use of hashmap to improve time efficiency when a new app is detected in
    // hook.AppProducer.java
    private Map<String, AppWrapper> appMap;

    // Required private constructor
    private AppMapSingleton() {
        appMap = new HashMap<>();
    }

    public static AppMapSingleton newInstance() {

        // Singleton implementation
        if (instance == null) {
            instance = new AppMapSingleton();
        }

        return instance;
    }

    public Map<String, AppWrapper> getAppMap() {
        return appMap;
    }
}
