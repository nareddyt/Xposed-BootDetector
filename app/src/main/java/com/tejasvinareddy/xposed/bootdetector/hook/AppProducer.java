package com.tejasvinareddy.xposed.bootdetector.hook;

import android.util.Log;
import com.tejasvinareddy.xposed.bootdetector.AppMapSingleton;
import com.tejasvinareddy.xposed.bootdetector.model.AppWrapper;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class AppProducer implements IXposedHookLoadPackage {

    private AppMapSingleton appMapSingleton;

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam)
            throws Throwable {

        // Set up App Map Singleton
        appMapSingleton = AppMapSingleton.newInstance();

        // Retrieve the name of the loaded package
        String loadedPackage = lpparam.packageName;
        XposedBridge.log("[BootDetector] " + loadedPackage);

        // Update the map as needed
        AppWrapper app = appMapSingleton.getAppMap().get(loadedPackage);
        if (app == null) {
            // The key does not exist in the map, so add it with a count of 1
            appMapSingleton.getAppMap().put(loadedPackage, new AppWrapper
                    (loadedPackage));
            // DEBUG
            Log.d("AppProducer", "Added entry");
        } else {
            // The key does exist in the map, so increment count by 1
            app.incrementLoadCount();
            // DEBUG
            Log.d("AppProducer", "Updated entry");
        }
    }
}
