package com.tejasvinareddy.xposed.bootdetector.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import com.tejasvinareddy.xposed.bootdetector.R;
import com.tejasvinareddy.xposed.bootdetector.model.AppWrapper;

import java.util.ArrayList;
import java.util.List;

// TODO sorting options

public class AppConsumerActivity extends AppCompatActivity {

    // Data variables
    private List<AppWrapper> appList = new ArrayList<>();

    // UI variables
    private SwipeRefreshLayout srl;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private void refreshRecyclerView() {

        // TODO update the App List

        // Set up the adapter
        adapter = new AppWrapperAdapter(appList,
                new AppWrapperAdapter.FeedInteractionListener() {
                    @Override
                    public void onAppClicked(AppWrapper app, int index) {
                        // TODO
                    }
                });
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);

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
