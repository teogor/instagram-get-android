package com.dolphpire.instamanage.ordersFragment.completed;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dolphpire.api.initializer.DolphPireApp;
import com.dolphpire.api.interfaces.OnOrdersRetrieved;
import com.dolphpire.api.models.OrderModel;
import com.dolphpire.instamanage.R;
import com.dolphpire.instamanage.ordersFragment.itemParser.adapter.AdapterOrders;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompletedFragment extends Fragment
{

    @BindView(R.id.rvOrders)
    RecyclerView rvOrders;
    private View mView;
    private Context mContext;
    private Activity mActivity;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<OrderModel> mDataList;
    private AdapterOrders mAdapter;
    private OrderModel mOrderModel;

    public CompletedFragment()
    {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        mView = inflater.inflate(R.layout.fragment_completed, container, false);
        ButterKnife.bind(this, mView);

        mActivity = getActivity();

        return mView;
    }

    @Override
    public void onStart()
    {
        super.onStart();
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
    }

    @Override
    public void onAttach(@NonNull Context context)
    {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {

        mDataList = new ArrayList<>();
        mAdapter = new AdapterOrders(mDataList, mActivity);

        rvOrders.setItemAnimator(new DefaultItemAnimator());
        linearLayoutManager = new LinearLayoutManager(mActivity);
        rvOrders.setLayoutManager(linearLayoutManager);
        rvOrders.setHasFixedSize(false);
        rvOrders.setAdapter(mAdapter);

        populateRecyclerView();

    }

    private void populateRecyclerView()
    {

        DolphPireApp.initializeApi()
                .orders()
                .retrieve()
                .completed()
                .addOnCompleteListener(dataList ->
                {
                    mDataList.addAll(dataList);
                    mAdapter.notifyDataSetChanged();
                })
                .execute();

    }

}