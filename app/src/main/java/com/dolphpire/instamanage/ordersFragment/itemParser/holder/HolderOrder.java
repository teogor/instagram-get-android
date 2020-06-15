package com.dolphpire.instamanage.ordersFragment.itemParser.holder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.dolphpire.instamanage.ordersFragment.itemParser.adapter.AdapterOrders;
import com.dolphpire.instamanage.ordersFragment.itemParser.model.ModelOrder;

import butterknife.ButterKnife;

public class HolderOrder extends RecyclerView.ViewHolder
{

//    @BindView(R.id.txtNoLikes)
//    TextView txtNoLikes;
    private ModelOrder mModelOrder;
    private AdapterOrders.OnItem listener;
    private int position;
    private Activity activity;

    public HolderOrder(View itemView)
    {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @SuppressLint("SetTextI18n")
    public void setContent(ModelOrder mModelOrder, AdapterOrders.OnItem listener, int position, Activity activity)
    {

        this.mModelOrder = mModelOrder;
        this.listener = listener;
        this.position = position;
        this.activity = activity;

    }

}