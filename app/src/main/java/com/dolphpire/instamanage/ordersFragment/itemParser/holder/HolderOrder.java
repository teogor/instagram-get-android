package com.dolphpire.instamanage.ordersFragment.itemParser.holder;

import android.app.Activity;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dolphpire.api.initializer.DolphPireApp;
import com.dolphpire.api.models.OrderModel;
import com.dolphpire.instamanage.R;
import com.dolphpire.instamanage.ordersFragment.itemParser.adapter.AdapterOrders;
import com.dolphpire.instamanage.views.DolphPireIS;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class HolderOrder extends RecyclerView.ViewHolder
{

    @BindView(R.id.imvIGUser)
    CircleImageView imvIGUser;
    @BindView(R.id.imvPostPreview)
    DolphPireIS imvPostPreview;
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

        imvIGUser.setVisibility(View.GONE);
        imvPostPreview.setVisibility(View.GONE);

        if (mOrderModel.getType() == 0)
        {
            //in progres
        } else if (mOrderModel.getType() == 1)
        {
            //completed
        }

        if (mOrderModel.getPostImage() != null)
        {
            //like order
            imvPostPreview.setVisibility(View.VISIBLE);
            Glide.with(activity)
                    .load(mOrderModel.getPostImage())
                    .into(imvPostPreview);
        } else
        {
            //follow order
            imvIGUser.setVisibility(View.VISIBLE);
            Glide.with(activity)
                    .load(mOrderModel.getProfilePicture())
                    .into(imvIGUser);
        }

    }

}