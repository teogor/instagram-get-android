package com.dolphpire.instamanage.igposts;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dolphpire.api.initializer.DolphPireApp;
import com.dolphpire.api.interfaces.OnIGPostsRetrieved;
import com.dolphpire.api.models.IGPostModel;
import com.dolphpire.api.models.IGPostsModel;
import com.dolphpire.insapi.manager.IGCommonFieldsManager;
import com.dolphpire.insapi.request.InsRequestCallBack;
import com.dolphpire.insapi.request.api.login.LoginRequest;
import com.dolphpire.insapi.request.api.login.LoginResponseData;
import com.dolphpire.instamanage.R;
import com.dolphpire.instamanage.igposts.adapter.AdapterIGPosts;
import com.dolphpire.instamanage.igposts.model.ModelIGPost;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IGPostsActivity extends AppCompatActivity
{

    @BindView(R.id.imvBack)
    ImageView imvBack;
    @BindView(R.id.rvIGPosts)
    RecyclerView rvIGPosts;
    private GridLayoutManager gridLayoutManager;
    private ArrayList<IGPostModel> mDataList;
    private AdapterIGPosts mAdapter;
    private ModelIGPost mModelIGPost;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ig_posts);

        ButterKnife.bind(this);

        imvBack.setOnClickListener(view -> finish());

        mDataList = new ArrayList<>();
        mAdapter = new AdapterIGPosts(mDataList, this);

        rvIGPosts.setItemAnimator(new DefaultItemAnimator());
        gridLayoutManager = new GridLayoutManager(this, 3);
        rvIGPosts.setLayoutManager(gridLayoutManager);
        rvIGPosts.setHasFixedSize(false);
        rvIGPosts.setAdapter(mAdapter);

        mAdapter.setListener(new AdapterIGPosts.OnItem()
        {
            @Override
            public void onSelected(int pos)
            {

            }
        });

        retrieveUserPosts();

    }

    private void retrieveUserPosts()
    {

        DolphPireApp.initializeApi().igAccount().posts()
                .withUserID(DolphPireApp.getInstance().getIGAccount().getIGID())
                .set()
                .addOnCompleteListener(mIGPostsModel ->
                {
                    mDataList.clear();

                    mDataList.addAll(mIGPostsModel.getPosts());

                    mAdapter.notifyDataSetChanged();

                })
                .execute();

    }

    @Override
    protected void onStart()
    {
        super.onStart();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }

}