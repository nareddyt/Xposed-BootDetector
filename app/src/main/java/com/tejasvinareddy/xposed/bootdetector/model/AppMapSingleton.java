package com.tejasvinareddy.xposed.bootdetector.model;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class AppMapSingleton {

    // FIXME use of synchronized + volatile

    // Single instance of this class
    private static volatile AppMapSingleton instance = null;

    // Use of hashmap to improve time efficiency when a new app is detected in
    // hook.AppProducer.java
    private volatile ConcurrentMap<String, AppWrapper> appMap;

    // Required private constructor
    private AppMapSingleton() {
        appMap = new ConcurrentHashMap<>();
        appMap.put("Test", new AppWrapper("Test"));
    }

    public static synchronized AppMapSingleton newInstance() {
        // Singleton implementation
        if (instance == null) {
            instance = new AppMapSingleton();
        }

        return instance;
    }

    public synchronized Collection<AppWrapper> getAppList() {
        return appMap.values();
    }

    public synchronized void addToAppMap(String loadedPackage) {

        // Update the map as needed
//        AppWrapper app = appMap.get(loadedPackage);
//        if (app == null) {
//            // The key does not exist in the map, so add it with a count of 1
            appMap.put(loadedPackage, new AppWrapper(loadedPackage));
//        } else {
//            // The key does exist in the map, so increment count by 1
//            app.incrementLoadCount();
//        }
    }
}
