package com.dolphpire.instamanage.ordersFragment.itemParser.holder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.dolphpire.api.models.OrderModel;
import com.dolphpire.instamanage.ordersFragment.itemParser.adapter.AdapterOrders;
import com.dolphpire.instamanage.ordersFragment.itemParser.model.ModelOrder;

import butterknife.ButterKnife;

public class HolderOrder extends RecyclerView.ViewHolder
{

    //    @BindView(R.id.txtNoLikes)
//    TextView txtNoLikes;
    private OrderModel mOrderModel;
    private AdapterOrders.OnItem listener;
    private int position;
    private Activity activity;

    public HolderOrder(View itemView)
    {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setContent(OrderModel mOrderModel, AdapterOrders.OnItem listener, int position, Activity activity)
    {

        this.mOrderModel = mOrderModel;
        this.listener = listener;
        this.position = position;
        this.activity = activity;

        if (position % 2 == 0)
        {

        } else
        {

        }

    }

}