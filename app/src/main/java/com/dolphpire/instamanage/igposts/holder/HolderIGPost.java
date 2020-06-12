package com.dolphpire.instamanage.igposts.holder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dolphpire.instamanage.R;
import com.dolphpire.instamanage.igposts.adapter.AdapterIGPosts;
import com.dolphpire.instamanage.igposts.model.ModelIGPost;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class HolderIGPost extends RecyclerView.ViewHolder
{

//    @BindView(R.id.rlIGAccount)
//    RelativeLayout rlIGAccount;
    private ModelIGPost mModelIGPost;
    private AdapterIGPosts.OnItem listener;
    private int position;
    private Activity activity;

    public HolderIGPost(View itemView)
    {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @SuppressLint("SetTextI18n")
    public void setContent(ModelIGPost mModelIGPost, AdapterIGPosts.OnItem listener, int position, Activity activity)
    {

        this.mModelIGPost = mModelIGPost;
        this.listener = listener;
        this.position = position;
        this.activity = activity;

    }

}