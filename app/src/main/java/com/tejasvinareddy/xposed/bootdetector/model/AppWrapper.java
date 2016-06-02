package com.tejasvinareddy.xposed.bootdetector.model;

public class AppWrapper {
    private String packageName;

    public AppWrapper(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageName() {
        return packageName;
    }
}
