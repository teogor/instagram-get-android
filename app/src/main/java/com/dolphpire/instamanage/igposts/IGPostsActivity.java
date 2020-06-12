package com.dolphpire.instamanage.igposts;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dolphpire.api.initializer.DolphPireApp;
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
    private ArrayList<ModelIGPost> mDataList;
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
                DolphPireApp.getInstance().setCurrentAccount(mDataList.get(pos).getIgAccountModel());

                LoginRequest loginRequest = new LoginRequest(mDataList.get(pos).getIgAccountModel().getUsername(), mDataList.get(pos).getIgAccountModel().getPassword());
                loginRequest.execute(new InsRequestCallBack<LoginResponseData>()
                {
                    @Override
                    public void onSuccess(int statusCode, LoginResponseData insBaseData)
                    {
                        LoginResponseData.LoggedInUserBean loggedInUserBean;
                        if (insBaseData != null && (loggedInUserBean = insBaseData.getLogged_in_user()) != null)
                        {
                            String pkId = String.valueOf(loggedInUserBean.getPk());
                            if (!TextUtils.isEmpty(pkId))
                            {
                                IGCommonFieldsManager.getInstance().savePKID(pkId);
                            }
                        }
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg)
                    {

                    }
                });
                populateRecyclerView();
            }
        });

        populateRecyclerView();
        retrieveUserPosts();

    }

    private void retrieveUserPosts()
    {

        DolphPireApp.initializeApi().igAccount().posts()
                .withUserID(DolphPireApp.getInstance().getIGAccount().getUsername())
                .set()
                .execute();

    }

    private void populateRecyclerView()
    {

        mDataList.clear();
        if (DolphPireApp.getInstance().getUser().getIGAccounts().size() != 0)
        {
            for (int i = 0; i < DolphPireApp.getInstance().getUser().getIGAccounts().size(); i++)
            {
                mModelIGPost = new ModelIGPost(0);
                mModelIGPost.setIGAccount(DolphPireApp.getInstance().getUser().getIGAccounts().get(i));
                mDataList.add(mModelIGPost);
                mModelIGPost = new ModelIGPost(0);
                mModelIGPost.setIGAccount(DolphPireApp.getInstance().getUser().getIGAccounts().get(i));
                mDataList.add(mModelIGPost);
                mModelIGPost = new ModelIGPost(0);
                mModelIGPost.setIGAccount(DolphPireApp.getInstance().getUser().getIGAccounts().get(i));
                mDataList.add(mModelIGPost);
                mModelIGPost = new ModelIGPost(0);
                mModelIGPost.setIGAccount(DolphPireApp.getInstance().getUser().getIGAccounts().get(i));
                mDataList.add(mModelIGPost);
            }
        }

        if (DolphPireApp.getInstance().getUser().getIGAccounts().size() <= 5)
        {

            //add ig account
            mModelIGPost = new ModelIGPost(1);
            mDataList.add(mModelIGPost);

        }

        //privacy and terms
        mModelIGPost = new ModelIGPost(2);
        mDataList.add(mModelIGPost);

        mAdapter.notifyDataSetChanged();

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