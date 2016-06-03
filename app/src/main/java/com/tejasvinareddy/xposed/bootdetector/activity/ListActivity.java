package com.tejasvinareddy.xposed.bootdetector.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import com.tejasvinareddy.xposed.bootdetector.AppMapSingleton;
import com.tejasvinareddy.xposed.bootdetector.R;
import com.tejasvinareddy.xposed.bootdetector.model.AppWrapper;
import com.tejasvinareddy.xposed.bootdetector.ui.AppWrapperAdapter;

// TODO sorting options
// FIXME should be a true consumer that only takes in one app at a time, not
//      an entire map

public class ListActivity extends AppCompatActivity {

    private AppMapSingleton appMapSingleton;

    private SwipeRefreshLayout srl;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private int count = 0;

    private void refreshRecyclerView() {

        // DEBUG
        count++;
        appMapSingleton.getAppMap().put("Test" + count, new AppWrapper("Test" +
                count));
        Log.d("DEBUG", "Added in debug entry");

        // Set up the adapter
        // FIXME list
        adapter = new AppWrapperAdapter(appMapSingleton.getAppMap(),
                new AppWrapperAdapter.FeedInteractionListener() {

                    @Override
                    public void onPostClicked(AppWrapper app, int index) {
                        // TODO
                    }
                });
        recyclerView.setAdapter(adapter);

        // DEBUG
        Log.d("List", "Refreshed!");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);

        // Set up App Map Singleton
        appMapSingleton = AppMapSingleton.newInstance();

        // Set up toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Xposed Boot Detector");

        // Set up recycler view
        recyclerView = (RecyclerView) findViewById(R.id.app_list_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Set up swipe-up-to-refresh layout
        srl = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshRecyclerView();
                recyclerView.invalidate();
                srl.setRefreshing(false);
            }
        });

        // Reset the recycler view to set the adapter
        refreshRecyclerView();
    }
}
