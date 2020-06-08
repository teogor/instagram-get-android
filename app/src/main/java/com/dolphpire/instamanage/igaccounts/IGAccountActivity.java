package com.dolphpire.instamanage.igaccounts;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dolphpire.api.initializer.DolphPireApp;
import com.dolphpire.api.models.SyncUserModel;
import com.dolphpire.api.models.UserModel;
import com.dolphpire.insapi.manager.IGCommonFieldsManager;
import com.dolphpire.insapi.request.InsRequestCallBack;
import com.dolphpire.insapi.request.api.header.GetHeaderRequest;
import com.dolphpire.insapi.request.api.login.LoginRequest;
import com.dolphpire.insapi.request.api.login.LoginResponseData;
import com.dolphpire.insapi.response.InsBaseResponseData;
import com.dolphpire.instamanage.R;
import com.dolphpire.instamanage.igaccounts.adapter.AdapterIGAccount;
import com.dolphpire.instamanage.igaccounts.model.ModelIGAccount;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IGAccountActivity extends AppCompatActivity
{

    @BindView(R.id.imvBack)
    ImageView imvBack;
    @BindView(R.id.rvIGAccounts)
    RecyclerView rvIGAccounts;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<ModelIGAccount> mDataList;
    private AdapterIGAccount mAdapter;
    private ModelIGAccount mModelIGAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ig_account);

        ButterKnife.bind(this);

        imvBack.setOnClickListener(view -> finish());

        mDataList = new ArrayList<>();
        mAdapter = new AdapterIGAccount(mDataList, this);

        rvIGAccounts.setItemAnimator(new DefaultItemAnimator());
        linearLayoutManager = new LinearLayoutManager(this);
        rvIGAccounts.setLayoutManager(linearLayoutManager);
        rvIGAccounts.setHasFixedSize(false);
        rvIGAccounts.setAdapter(mAdapter);

        mAdapter.setListener(new AdapterIGAccount.OnItem()
        {
            @Override
            public void onSelected(int pos)
            {
                getCsftoken();
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

        DolphPireApp.getInstance().syncUser()
                .setListener(user -> populateRecyclerView(), "IG_ACCOUNT_ACTIVITY");

    }

    private void getCsftoken()
    {
        final GetHeaderRequest getHeaderRequest = new GetHeaderRequest();
        getHeaderRequest.execute(new InsRequestCallBack()
        {
            @Override
            public void onSuccess(int statusCode, InsBaseResponseData insBaseData)
            {
                String csrfCookie = getHeaderRequest.getCsrfCookie();
            }

            @Override
            public void onFailure(int errorCode, String errorMsg)
            {

            }
        });

    }

    private void populateRecyclerView()
    {

        mDataList.clear();
        if (DolphPireApp.getInstance().getUser().getIGAccounts().size() != 0)
        {
            for (int i = 0; i < DolphPireApp.getInstance().getUser().getIGAccounts().size(); i++)
            {
                mModelIGAccount = new ModelIGAccount(0);
                mModelIGAccount.setIGAccount(DolphPireApp.getInstance().getUser().getIGAccounts().get(i));
                mDataList.add(mModelIGAccount);
            }
        }

        if (DolphPireApp.getInstance().getUser().getIGAccounts().size() <= 5)
        {

            //add ig account
            mModelIGAccount = new ModelIGAccount(1);
            mDataList.add(mModelIGAccount);

        }

        //privacy and terms
        mModelIGAccount = new ModelIGAccount(2);
        mDataList.add(mModelIGAccount);

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