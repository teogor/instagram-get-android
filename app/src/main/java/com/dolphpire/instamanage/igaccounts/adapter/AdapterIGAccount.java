package com.dolphpire.instamanage.igaccounts.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dolphpire.instamanage.R;
import com.dolphpire.instamanage.igaccounts.holder.HolderIGAccount;
import com.dolphpire.instamanage.igaccounts.model.ModelIGAccount;

import java.util.ArrayList;

public class AdapterIGAccount extends RecyclerView.Adapter<HolderIGAccount> {

    private Activity activity;
    private ArrayList<ModelIGAccount> mDataList;
    private OnItem listener;

    public AdapterIGAccount(ArrayList<ModelIGAccount> mDataList, Activity activity) {
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
    public HolderIGAccount onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ig_accounts, parent, false);
        return new HolderIGAccount(v);

    }

    @Override
    public void onBindViewHolder(HolderIGAccount holder, int position) {
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
