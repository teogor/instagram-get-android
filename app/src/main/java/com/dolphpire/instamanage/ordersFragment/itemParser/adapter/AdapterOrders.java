package com.dolphpire.instamanage.ordersFragment.itemParser.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dolphpire.api.models.IGPostModel;
import com.dolphpire.api.models.OrderModel;
import com.dolphpire.instamanage.R;
import com.dolphpire.instamanage.ordersFragment.itemParser.holder.HolderOrder;
import com.dolphpire.instamanage.ordersFragment.itemParser.model.ModelOrder;

import java.util.ArrayList;

public class AdapterOrders extends RecyclerView.Adapter<HolderOrder> {

    private Activity activity;
    private ArrayList<OrderModel> mDataList;
    private OnItem listener;

    public AdapterOrders(ArrayList<OrderModel> mDataList, Activity activity) {
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
    public HolderOrder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new HolderOrder(v);

    }

    @Override
    public void onBindViewHolder(HolderOrder holder, int position) {
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
