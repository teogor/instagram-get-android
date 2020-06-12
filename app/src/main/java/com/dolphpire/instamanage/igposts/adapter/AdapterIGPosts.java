package com.dolphpire.instamanage.igposts.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dolphpire.instamanage.R;
import com.dolphpire.instamanage.igposts.holder.HolderIGPost;
import com.dolphpire.instamanage.igposts.model.ModelIGPost;

import java.util.ArrayList;

public class AdapterIGPosts extends RecyclerView.Adapter<HolderIGPost> {

    private Activity activity;
    private ArrayList<ModelIGPost> mDataList;
    private OnItem listener;

    public AdapterIGPosts(ArrayList<ModelIGPost> mDataList, Activity activity) {
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
    public HolderIGPost onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ig_accounts, parent, false);
        return new HolderIGPost(v);

    }

    @Override
    public void onBindViewHolder(HolderIGPost holder, int position) {
        holder.setContent(mDataList.get(position), listener, position, activity);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public interface OnItem {
        void onSelected(int pos);
    }


}
