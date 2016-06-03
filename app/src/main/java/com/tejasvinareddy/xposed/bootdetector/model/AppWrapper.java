package com.tejasvinareddy.xposed.bootdetector.model;

public class AppWrapper implements Comparable<AppWrapper> {
    private String packageName;
    private int loadCount;

    public AppWrapper(String packageName) {
        this.packageName = packageName;
        loadCount = 1;
    }

    @Override
    public int compareTo(AppWrapper another) {
        return packageName.compareTo(another.packageName);
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
