package com.dolphpire.instamanage.getFollowersFragment.holder;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dolphpire.instamanage.R;
import com.dolphpire.instamanage.getFollowersFragment.adapter.AdapterGetFollowers;
import com.dolphpire.instamanage.getFollowersFragment.model.ModelGetFollowers;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HolderGetFollowers extends RecyclerView.ViewHolder {

    @BindView(R.id.txtAmountCoins)
    TextView txtAmountCoins;
    @BindView(R.id.txtAmountFollowers)
    TextView txtAmountFollowers;
    @BindView(R.id.rlGetFollowers)
    RelativeLayout rlGetFollowers;

    public HolderGetFollowers(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @SuppressLint("SetTextI18n")
    public void setContent(ModelGetFollowers mModelGetFollowers, AdapterGetFollowers.OnItem listener, int position) {

        txtAmountCoins.setText(String.valueOf(mModelGetFollowers.getCoins()));
        txtAmountFollowers.setText(String.valueOf(mModelGetFollowers.getFollowers()));

        rlGetFollowers.setOnClickListener(v -> {
            if (listener != null) {
                listener.onPosition(position);
            }
        });

    }

}