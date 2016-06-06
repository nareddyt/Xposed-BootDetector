package com.tejasvinareddy.xposed.bootdetector.model;

import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;

// FIXME use of synchronized + volatile
// TODO change to AppWrapper
// TODO throws clauses

public class AppQueueSingleton {

    // Single instance of this class
    private static volatile AppQueueSingleton instance = null;
    private volatile BlockingQueue<String> queue;

    // Required private constructor
    private AppQueueSingleton() {
        queue = new LinkedBlockingQueue<>();

        try {
            queue.put("Test");
        } catch (InterruptedException e) {
            // DEBUG
            e.printStackTrace();
        }
    }

    public static synchronized AppQueueSingleton newInstance() {
        // Singleton implementation
        if (instance == null) {
            instance = new AppQueueSingleton();
        }

        return instance;
    }

    public void putApp(String packageName) {
        try {
            queue.put(packageName);
        } catch (InterruptedException e) {
            // DEBUG
            e.printStackTrace();
        }
    }

    public String takeApp() {
        try {
            return queue.take();
        } catch (InterruptedException e) {
            // DEBUG
            e.printStackTrace();
        }

        // FIXME
        return null;
    }

    public boolean hasApps() {
        return queue.size() != 0;
    }
}
