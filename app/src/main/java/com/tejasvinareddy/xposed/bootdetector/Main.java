package com.tejasvinareddy.xposed.bootdetector;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tnareddy on 5/30/16.
 */
public class Main implements IXposedHookLoadPackage {
    private Map<String, Integer> bootCountMap = new HashMap<>();

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam)
            throws Throwable {

        // Retrieve the name of the loaded package
        String loadedPackage = lpparam.packageName;
        XposedBridge.log("[BootDetector] " + loadedPackage);

        // Update the map as needed
        Integer mapCount = bootCountMap.get(loadedPackage);
        if (mapCount == null) {
            // The key does not exist in the map, so add it with a count of 1
            bootCountMap.put(loadedPackage, 1);
        } else {
            // The key does exist in the map, so increment count by 1
            bootCountMap.put(loadedPackage, mapCount + 1);
        }

    }
}
