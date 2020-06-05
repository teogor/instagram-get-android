package com.dolphpire.instamanage.getfollowersfragment.holder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.dolphpire.instamanage.getfollowersfragment.adapter.AdapterGetFollowers;
import com.dolphpire.instamanage.getfollowersfragment.model.ModelGetFollowers;

import butterknife.ButterKnife;

public class HolderGetFollowers extends RecyclerView.ViewHolder {


    public HolderGetFollowers(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @SuppressLint("SetTextI18n")
    public void setContent(ModelGetFollowers mModelGetFollowers, Activity mActivity, AdapterGetFollowers.ChangeListener listener) {


    }

}