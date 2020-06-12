package com.dolphpire.instamanage.igposts.holder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dolphpire.api.initializer.DolphPireApp;
import com.dolphpire.instamanage.R;
import com.dolphpire.instamanage.iglogin.IGLoginActivity;
import com.dolphpire.instamanage.igposts.adapter.AdapterIGPosts;
import com.dolphpire.instamanage.igposts.model.ModelIGPost;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class HolderIGPost extends RecyclerView.ViewHolder
{

    @BindView(R.id.rlIGAccount)
    RelativeLayout rlIGAccount;
    @BindView(R.id.rlAddAccount)
    RelativeLayout rlAddAccount;
    @BindView(R.id.rlTermsPrivacy)
    RelativeLayout rlTermsPrivacy;
    @BindView(R.id.imvIGUser)
    CircleImageView imvIGUser;
    @BindView(R.id.txtIGUsername)
    TextView txtIGUsername;
    @BindView(R.id.imvDelete)
    ImageView imvDelete;
    @BindView(R.id.imvSelected)
    ImageView imvSelected;
    @BindView(R.id.llAddAccount)
    LinearLayout llAddAccount;
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

        rlIGAccount.setVisibility(View.GONE);
        rlAddAccount.setVisibility(View.GONE);
        rlTermsPrivacy.setVisibility(View.GONE);
        if (mModelIGPost.getType() == 0)
        {
            loadIGAccount();
        } else if (mModelIGPost.getType() == 1)
        {
            loadAddAccount();
        } else if (mModelIGPost.getType() == 2)
        {
            loadTermsPrivacy();
        }

    }

    private void loadTermsPrivacy()
    {

        rlTermsPrivacy.setVisibility(View.VISIBLE);

    }

    private void loadAddAccount()
    {

        rlAddAccount.setVisibility(View.VISIBLE);

        llAddAccount.setOnClickListener(v ->
        {
            Intent intent = new Intent(activity, IGLoginActivity.class);
            activity.startActivity(intent);
        });


    }

    @SuppressLint("SetTextI18n")
    private void loadIGAccount()
    {

        rlIGAccount.setVisibility(View.VISIBLE);

        rlIGAccount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (listener != null)
                {
                    listener.onSelected(position);
                }
            }
        });

        imvDelete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

        Glide.with(activity)
                .load(mModelIGPost.getIgAccountModel().getProfilePicture())
                .into(imvIGUser);

        txtIGUsername.setText("@" + mModelIGPost.getIgAccountModel().getUsername());

        if (DolphPireApp.getInstance().getIGAccount() != null)
        {
            if (DolphPireApp.getInstance().getIGAccount().getIGID() == mModelIGPost.getIgAccountModel().getIGID())
            {
                imvSelected.setVisibility(View.VISIBLE);
            } else
            {
                imvSelected.setVisibility(View.GONE);
            }
        } else
        {
            imvSelected.setVisibility(View.GONE);
        }

    }

}