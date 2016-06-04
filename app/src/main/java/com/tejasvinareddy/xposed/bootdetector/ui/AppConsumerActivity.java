package com.tejasvinareddy.xposed.bootdetector.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import com.tejasvinareddy.xposed.bootdetector.model.AppMapSingleton;
import com.tejasvinareddy.xposed.bootdetector.R;
import com.tejasvinareddy.xposed.bootdetector.model.AppWrapper;

// TODO sorting options
// FIXME should be a true consumer that only takes in one app at a time, not
//      an entire map

public class AppConsumerActivity extends AppCompatActivity {

    private AppMapSingleton appMapSingleton;

    private SwipeRefreshLayout srl;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private void refreshRecyclerView() {

        // Set up the adapter
        adapter = new AppWrapperAdapter(appMapSingleton.getAppList(),
                new AppWrapperAdapter.FeedInteractionListener() {

                    @Override
                    public void onPostClicked(AppWrapper app, int index) {
                        // TODO
                    }
                });
        recyclerView.setAdapter(adapter);
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
                srl.setRefreshing(false);
            }
        });

        // Reset the recycler view to set the adapter
        refreshRecyclerView();
    }
}
