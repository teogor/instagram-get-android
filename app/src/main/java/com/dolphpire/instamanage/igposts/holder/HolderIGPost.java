package com.dolphpire.instamanage.igposts.holder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dolphpire.api.initializer.DolphPireApp;
import com.dolphpire.api.models.IGPostModel;
import com.dolphpire.instamanage.R;
import com.dolphpire.instamanage.igposts.adapter.AdapterIGPosts;
import com.dolphpire.instamanage.igposts.model.ModelIGPost;
import com.dolphpire.instamanage.views.DolphPireIS;
import com.dolphpire.instamanage.views.DolphPireRL;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.dolphpire.api.utils.NumberFormat.numberFormat;

public class HolderIGPost extends RecyclerView.ViewHolder
{

    @BindView(R.id.txtNoLikes)
    TextView txtNoLikes;
    @BindView(R.id.imvPostPreview)
    DolphPireIS imvPostPreview;
    @BindView(R.id.rlImgHolder)
    DolphPireRL rlImgHolder;
    private IGPostModel mIGPostModel;
    private AdapterIGPosts.OnItem listener;
    private int position;
    private Activity activity;

    public HolderIGPost(View itemView)
    {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @SuppressLint("SetTextI18n")
    public void setContent(IGPostModel mIGPostModel, AdapterIGPosts.OnItem listener, int position, Activity activity)
    {

        this.mIGPostModel = mIGPostModel;
        this.listener = listener;
        this.position = position;
        this.activity = activity;

        txtNoLikes.setText(numberFormat(mIGPostModel.getLikes()));

        Glide.with(activity)
                .load(mIGPostModel.getImg150x150())
                .into(imvPostPreview);

        rlImgHolder.setOnClickListener(v ->
        {
            if (listener != null) {
                listener.onSelected(position);
            }
        });

    }

}