package com.tejasvinareddy.xposed.bootdetector.hook;

import android.util.Log;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class AppProducer implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam)
            throws Throwable {

        // Retrieve the name of the loaded package
        String loadedPackage = lpparam.packageName;
        Log.d("BootDetector", "[Producer] Detected: " + loadedPackage);

        // TODO produce wrapped app
    }
}
