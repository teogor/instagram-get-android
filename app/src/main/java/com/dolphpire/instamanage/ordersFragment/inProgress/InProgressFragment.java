package com.dolphpire.instamanage.ordersFragment.inProgress;

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

import com.dolphpire.instamanage.R;
import com.dolphpire.instamanage.igaccounts.adapter.AdapterIGAccount;
import com.dolphpire.instamanage.igaccounts.model.ModelIGAccount;
import com.dolphpire.instamanage.ordersFragment.itemParser.adapter.AdapterOrders;
import com.dolphpire.instamanage.ordersFragment.itemParser.model.ModelOrder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InProgressFragment extends Fragment
{

    @BindView(R.id.rvOrders)
    RecyclerView rvOrders;
    private View mView;
    private Context mContext;
    private Activity mActivity;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<ModelOrder> mDataList;
    private AdapterOrders mAdapter;
    private ModelOrder mModelOrder;

    public InProgressFragment()
    {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        mView = inflater.inflate(R.layout.fragment_in_progress, container, false);
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

        mModelOrder = new ModelOrder(0);
        mDataList.add(mModelOrder);

        mModelOrder = new ModelOrder(0);
        mDataList.add(mModelOrder);

        mModelOrder = new ModelOrder(0);
        mDataList.add(mModelOrder);

        mModelOrder = new ModelOrder(0);
        mDataList.add(mModelOrder);

        mModelOrder = new ModelOrder(0);
        mDataList.add(mModelOrder);

        mModelOrder = new ModelOrder(0);
        mDataList.add(mModelOrder);

        mAdapter.notifyDataSetChanged();

    }

}