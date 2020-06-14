package com.dolphpire.instamanage.getLikesFragment.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dolphpire.instamanage.R;
import com.dolphpire.instamanage.getLikesFragment.holder.HolderGetLikes;
import com.dolphpire.instamanage.getLikesFragment.model.ModelGetLikes;

import java.util.ArrayList;

public class AdapterGetLikes extends RecyclerView.Adapter<HolderGetLikes> {

    private Activity activity;
    private ArrayList<ModelGetLikes> mDataList;
    private OnItem listener;

    public AdapterGetLikes(ArrayList<ModelGetLikes> mDataList, Activity activity) {
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
    public HolderGetLikes onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_get_likes, parent, false);
        return new HolderGetLikes(v);

    }

    @Override
    public void onBindViewHolder(HolderGetLikes holder, int position) {
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
