package com.tejasvinareddy.xposed.bootdetector.model;

import android.app.Application;
import android.util.Log;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

// FIXME use of synchronized + volatile
// TODO change to AppWrapper
// TODO throws clauses

public class AppQueueSingleton extends Application {

    // Single instance of this class
    private static volatile AppQueueSingleton instance = new
            AppQueueSingleton();
    private volatile BlockingQueue<String> queue;

    // FIXME change to private but manifest
    public AppQueueSingleton() {
        queue = new LinkedBlockingQueue<>();
    }

    public static synchronized AppQueueSingleton newInstance() {
        // Singleton Implementation
        Log.d("BootDetector", "[Queue] Using instance: " + instance.toString());
        return instance;
    }

    public synchronized boolean hasApps() {
        return queue.size() != 0;
    }

    public synchronized void putApp(String packageName) {
        try {
            queue.put(packageName);
            Log.d("BootDetector", "[Queue] Added: " + packageName);
        } catch (InterruptedException e) {
            // DEBUG
            e.printStackTrace();
        }
    }

    public synchronized String takeApp() {
        if (queue.size() == 0) {
            throw new IllegalAccessError("[Queue] Queue size not checked!");
        }

        try {
            Log.d("BootDetector", "[Queue] Took an app");
            return queue.take();
        } catch (InterruptedException e) {
            // DEBUG
            e.printStackTrace();
        }

        // FIXME
        return null;
    }
}
