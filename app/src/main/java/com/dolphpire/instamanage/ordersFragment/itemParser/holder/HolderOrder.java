package com.dolphpire.instamanage.ordersFragment.itemParser.holder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.txtCounter)
    TextView txtCounter;
    @BindView(R.id.txtOrderType)
    TextView txtOrderType;
    @BindView(R.id.imvDelete)
    ImageView imvDelete;
    private OrderModel mOrderModel;
    private AdapterOrders.OnItem listener;
    private int position;
    private Activity activity;

    public HolderOrder(View itemView)
    {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @SuppressLint("SetTextI18n")
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
            txtOrderType.setText("Get Likes");
        } else
        {
            //follow order
            imvIGUser.setVisibility(View.VISIBLE);
            Glide.with(activity)
                    .load(mOrderModel.getProfilePicture())
                    .into(imvIGUser);
            txtOrderType.setText("Get Followers");
        }

        progressBar.setMax(mOrderModel.getTarget());
        progressBar.setProgress(mOrderModel.getInteractionCount());

        int interactionCount = Math.min(mOrderModel.getInteractionCount(), mOrderModel.getTarget());
        txtCounter.setText(interactionCount + "/" + mOrderModel.getTarget());

        if (interactionCount == mOrderModel.getTarget())
        {
            imvDelete.setVisibility(View.GONE);
        } else
        {
            imvDelete.setVisibility(View.VISIBLE);
        }

    }

}