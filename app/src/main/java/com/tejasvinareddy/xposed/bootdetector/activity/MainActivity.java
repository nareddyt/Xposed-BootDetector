package com.tejasvinareddy.xposed.bootdetector.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.tejasvinareddy.xposed.bootdetector.R;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements
        IXposedHookLoadPackage {

    private Map<String, Integer> bootCountMap;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bootCountMap = new HashMap<>();
    }
}
