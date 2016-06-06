package com.tejasvinareddy.xposed.bootdetector.hook;

import android.util.Log;
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
        Log.d("BootDetector", "[Producer] New instance of: " + appQueueSingleton
                .toString());

        // Retrieve the name of the loaded package
        String loadedPackage = lpparam.packageName;
        Log.d("BootDetector", "[Producer] Detected: " + loadedPackage);

        // Place in the App Queue Singleton
        appQueueSingleton.putApp(loadedPackage);
    }
}
