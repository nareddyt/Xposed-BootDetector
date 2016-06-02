package com.tejasvinareddy.xposed.bootdetector.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.tejasvinareddy.xposed.bootdetector.R;
import com.tejasvinareddy.xposed.bootdetector.model.AppWrapper;

import java.util.ArrayList;
import java.util.Map;

/**
 * Adapter for displaying cardviews that represent apps in a RecyclerView.
 */
public class AppWrapperAdapter extends RecyclerView.Adapter<AppWrapperAdapter
        .ViewHolder> {

    private ArrayList<AppWrapper> apps;
    private FeedInteractionListener listener;

    public AppWrapperAdapter(Map<String, AppWrapper> apps,
                             FeedInteractionListener feedInteractionListener) {
        this(new ArrayList<AppWrapper>(apps.values()), feedInteractionListener);
    }

    public AppWrapperAdapter(ArrayList<AppWrapper> apps, FeedInteractionListener
            feedInteractionListener) {
        this.apps = apps;
        listener = feedInteractionListener;
    }

    public void add(AppWrapper app) {
        apps.add(app);
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.view_app, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        viewHolder.packageName.setText(apps.get(i).getPackageName());
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }

    public interface FeedInteractionListener {
        void onPostClicked(AppWrapper app, int index);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView packageName;
        public ImageView image;

        public ViewHolder(View appViewLayout) {
            super(appViewLayout);
            packageName = (TextView) appViewLayout.findViewById(R.id.app_name);
            image = (ImageView) appViewLayout.findViewById(R.id.app_image);
        }
    }
}
