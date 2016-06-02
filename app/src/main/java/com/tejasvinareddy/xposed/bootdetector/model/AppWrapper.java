package com.tejasvinareddy.xposed.bootdetector.model;

public class AppWrapper {
    private String packageName;
    private int loadCount;

    public AppWrapper(String packageName) {
        this.packageName = packageName;
        loadCount = 1;
    }

    public int getLoadCount() {
        return loadCount;
    }

    public String getPackageName() {
        return packageName;
    }

    public void incrementLoadCount() {
        loadCount++;
    }
}
