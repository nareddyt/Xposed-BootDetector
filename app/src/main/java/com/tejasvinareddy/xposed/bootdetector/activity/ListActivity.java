package com.tejasvinareddy.xposed.bootdetector.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.tejasvinareddy.xposed.bootdetector.R;
import com.tejasvinareddy.xposed.bootdetector.model.AppWrapper;
import com.tejasvinareddy.xposed.bootdetector.ui.AppWrapperAdapter;

import java.util.HashMap;
import java.util.Map;

// TODO sorting options

public class ListActivity extends AppCompatActivity {

    // Use of hashmap to improve time efficiency when a new app is detected in
    // hook.Main.java
    private static Map<String, AppWrapper> appMap = new HashMap<>();

    private SwipeRefreshLayout srl;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private int count = 0;

    private void refreshRecyclerView() {

        // DEBUG
        count++;
        appMap.put("Test" + count, new AppWrapper("Test" + count));

        // FIXME list
        adapter = new AppWrapperAdapter(appMap,
                new AppWrapperAdapter.FeedInteractionListener() {

                    @Override
                    public void onPostClicked(AppWrapper app, int index) {
                        // TODO

                    }
                });

        recyclerView.setAdapter(adapter);
        Log.d("List", "Refreshed!");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);

        // FIXME
        setTitle("Xposed Boot Detector");

        recyclerView = (RecyclerView) findViewById(R.id.app_list_recycler_view);

        srl = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshRecyclerView();
                recyclerView.invalidate();
                srl.setRefreshing(false);
            }
        });

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        refreshRecyclerView();
    }

    public static Map<String, AppWrapper> getAppMap() {
        // FIXME encapsulation
        Log.d("List", "Returned map");
        return appMap;
    }
}
