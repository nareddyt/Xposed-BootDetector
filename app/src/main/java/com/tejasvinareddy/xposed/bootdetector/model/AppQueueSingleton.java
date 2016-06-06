package com.tejasvinareddy.xposed.bootdetector.model;

import android.util.Log;

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

        putApp("Test");
    }

    public static synchronized AppQueueSingleton newInstance() {
        // Singleton implementation
        if (instance == null) {
            instance = new AppQueueSingleton();
            Log.d("BootDetector", "New Instance made!!!");
        }

        Log.d("BootDetector", instance.toString());
        return instance;
    }

    public void putApp(String packageName) {
        try {
            queue.put(packageName);
            Log.d("BootDetector", "Added " + packageName);
        } catch (InterruptedException e) {
            // DEBUG
            e.printStackTrace();
        }
    }

    public String takeApp() {
        if (queue.size() == 0) {
            throw new IllegalAccessError("Queue size not checked!");
        }

        try {
            Log.d("BootDetector", "Took an app");
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
