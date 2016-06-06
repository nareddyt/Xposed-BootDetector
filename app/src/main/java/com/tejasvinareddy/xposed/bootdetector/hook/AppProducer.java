package com.tejasvinareddy.xposed.bootdetector.hook;

import com.tejasvinareddy.xposed.bootdetector.model.AppQueueSingleton;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

// FIXME should be a true producer that only introduces 1 app at a time, not
//      a full map

public class AppProducer implements IXposedHookLoadPackage {

    private AppQueueSingleton appQueueSingleton;

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam)
            throws Throwable {

        // Set up App Queue Singleton
        appQueueSingleton = AppQueueSingleton.newInstance();
        XposedBridge.log("[BootDetector] " + appQueueSingleton);

        // Retrieve the name of the loaded package
        String loadedPackage = lpparam.packageName;
        XposedBridge.log("[BootDetector] " + loadedPackage);

        // Place in the App Queue Singleton
        appQueueSingleton.putApp("$Test$");
        appQueueSingleton.putApp(loadedPackage);
    }
}
