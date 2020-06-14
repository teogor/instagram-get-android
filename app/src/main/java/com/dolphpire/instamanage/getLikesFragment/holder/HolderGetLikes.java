package com.dolphpire.instamanage.getLikesFragment.holder;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dolphpire.instamanage.R;
import com.dolphpire.instamanage.getLikesFragment.adapter.AdapterGetLikes;
import com.dolphpire.instamanage.getLikesFragment.model.ModelGetLikes;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HolderGetLikes extends RecyclerView.ViewHolder {

    @BindView(R.id.txtAmountCoins)
    TextView txtAmountCoins;
    @BindView(R.id.txtAmountLikes)
    TextView txtAmountLikes;
    @BindView(R.id.rlGetLikes)
    RelativeLayout rlGetLikes;

    public HolderGetLikes(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @SuppressLint("SetTextI18n")
    public void setContent(ModelGetLikes mModelGetLikes, AdapterGetLikes.OnItem listener, int position) {

        txtAmountCoins.setText(String.valueOf(mModelGetLikes.getCoins()));
        txtAmountLikes.setText(String.valueOf(mModelGetLikes.getLikes()));

        rlGetLikes.setOnClickListener(v -> {
            if (listener != null) {
                listener.onPosition(position);
            }
        });

    }

}