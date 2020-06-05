package com.dolphpire.instamanage.igaccounts;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dolphpire.instamanage.R;
import com.dolphpire.instamanage.getfollowersfragment.model.ModelGetFollowers;
import com.dolphpire.instamanage.igaccounts.adapter.AdapterIGAccount;
import com.dolphpire.instamanage.igaccounts.model.ModelIGAccount;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IGAccountActivity extends AppCompatActivity {

    @BindView(R.id.imvBack)
    ImageView imvBack;
    @BindView(R.id.rvIGAccounts)
    RecyclerView rvIGAccounts;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<ModelIGAccount> mDataList;
    private AdapterIGAccount mAdapter;
    private ModelIGAccount mModelIGAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        populateRecyclerView();

    }

    private void populateRecyclerView() {

        //ig account
        mModelIGAccount = new ModelIGAccount(0);
        mDataList.add(mModelIGAccount);

        //ig account
        mModelIGAccount = new ModelIGAccount(0);
        mDataList.add(mModelIGAccount);

        //add ig account
        mModelIGAccount = new ModelIGAccount(1);
        mDataList.add(mModelIGAccount);

        //privacy and terms
        mModelIGAccount = new ModelIGAccount(2);
        mDataList.add(mModelIGAccount);

        mAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}