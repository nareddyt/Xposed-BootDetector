package com.tejasvinareddy.xposed.bootdetector;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class MainActivity extends AppCompatActivity implements
        IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam)
            throws Throwable {
        XposedBridge.log("[BootDetector] " + lpparam.packageName);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
