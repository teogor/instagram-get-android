package com.dolphpire.instamanage.getFollowersFragment.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dolphpire.instamanage.R;
import com.dolphpire.instamanage.getFollowersFragment.holder.HolderGetFollowers;
import com.dolphpire.instamanage.getFollowersFragment.model.ModelGetFollowers;

import java.util.ArrayList;

public class AdapterGetFollowers extends RecyclerView.Adapter<HolderGetFollowers> {

    private Activity activity;
    private ArrayList<ModelGetFollowers> mDataList;
    private OnItem listener;

    public AdapterGetFollowers(ArrayList<ModelGetFollowers> mDataList, Activity activity) {
        this.mDataList = mDataList;
        this.activity = activity;
    }

    public OnItem getListener() {
        return listener;
    }

    public void setListener(OnItem listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public HolderGetFollowers onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_get_followers, parent, false);
        return new HolderGetFollowers(v);

    }

    @Override
    public void onBindViewHolder(HolderGetFollowers holder, int position) {
        holder.setContent(mDataList.get(position), listener, position);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public interface OnItem {
        void onPosition(int pos);
    }


}
