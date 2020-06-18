package com.dolphpire.instamanage.igposts;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.dolphpire.api.initializer.DolphPireApp;
import com.dolphpire.api.models.IGPostModel;
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
    @BindView(R.id.srlRefreshPosts)
    SwipeRefreshLayout srlRefreshPosts;
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

        mAdapter.setListener(pos ->
        {
            DolphPireApp.getInstance()
                    .syncIGPost()
                    .setNewPost(mDataList.get(pos));

            DolphPireApp.getInstance()
                    .setPost(mDataList.get(pos));

            finish();

        });

        srlRefreshPosts.setProgressViewOffset(
                false,
                getResources().getDimensionPixelSize(R.dimen.refresher_offset),
                getResources().getDimensionPixelSize(R.dimen.refresher_offset_end)
        );

        srlRefreshPosts.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                retrieveUserPosts();
            }
        });

        if (DolphPireApp.getInstance().getIGAccount() != null)
        {
            if (DolphPireApp.getInstance().getIGAccount().getIGPosts() != null)
            {
                mDataList.clear();

                mDataList.addAll(DolphPireApp.getInstance().getIGAccount().getIGPosts().getPosts());

                mAdapter.notifyDataSetChanged();
            }
        }
        retrieveUserPosts();

    }

    private void retrieveUserPosts()
    {

        srlRefreshPosts.setRefreshing(true);
        DolphPireApp.initializeApi().igAccount().posts()
                .withUserID(DolphPireApp.getInstance().getIGAccount().getIGID())
                .set()
                .addOnCompleteListener(mIGPostsModel ->
                {
                    mDataList.clear();

                    mDataList.addAll(mIGPostsModel.getPosts());

                    mAdapter.notifyDataSetChanged();

                    DolphPireApp.getInstance()
                            .setIGPosts(mIGPostsModel);

                    srlRefreshPosts.setRefreshing(false);

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