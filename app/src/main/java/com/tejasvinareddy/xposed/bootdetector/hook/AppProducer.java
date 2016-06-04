package com.tejasvinareddy.xposed.bootdetector.hook;

import android.content.Intent;
import com.tejasvinareddy.xposed.bootdetector.model.AppMapSingleton;
import com.tejasvinareddy.xposed.bootdetector.model.AppWrapper;
import com.tejasvinareddy.xposed.bootdetector.ui.AppConsumerActivity;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

// FIXME should be a true producer that only introduces 1 app at a time, not
//      a full map

public class AppProducer implements IXposedHookLoadPackage {

    private AppMapSingleton appMapSingleton;

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam)
            throws Throwable {

        // Set up App Map Singleton
        appMapSingleton = AppMapSingleton.newInstance();
        XposedBridge.log("[BootDetector] " + appMapSingleton);

        // Retrieve the name of the loaded package
        String loadedPackage = lpparam.packageName;
        XposedBridge.log("[BootDetector] " + loadedPackage);

        appMapSingleton.addToAppMap("$Test$");
        XposedBridge.log(appMapSingleton.getAppList().toString());

        appMapSingleton.addToAppMap(loadedPackage);
    }
}
