package com.tejasvinareddy.xposed.bootdetector.model;

public class AppWrapper implements Comparable<AppWrapper> {
    private final String packageName;
    private int loadCount;

    public AppWrapper(String packageName) {

        if (packageName == null) {
            throw new IllegalArgumentException("Invalid package name!");
        }

        this.packageName = packageName;
        loadCount = 1;
    }

    @Override
    public int compareTo(AppWrapper another) {
        return packageName.compareTo(another.packageName);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof AppWrapper)) {
            return false;
        }

        AppWrapper aw = (AppWrapper) other;

        return aw.packageName.equals(packageName);
    }

    @Override
    public String toString() {
        return packageName + "-" + super.toString();
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
