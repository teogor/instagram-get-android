package com.dolphpire.instamanage.getfollowersfragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.dolphpire.instamanage.R;
import com.dolphpire.instamanage.getfollowersfragment.adapter.AdapterGetFollowers;
import com.dolphpire.instamanage.getfollowersfragment.model.ModelGetFollowers;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GetFollowersFragment extends Fragment {

    private View mView;
    private Context mContext;
    private Activity mActivity;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<ModelGetFollowers> mDataList;
    private AdapterGetFollowers mAdapter;
    private ModelGetFollowers mModelGetFollowers;

    @BindView(R.id.rvGetFollowers)
    RecyclerView rvGetFollowers;

    public GetFollowersFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_get_followers, container, false);
        ButterKnife.bind(this, mView);

        mActivity = getActivity();

        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        mDataList = new ArrayList<>();
        mAdapter = new AdapterGetFollowers(mDataList, mActivity);
        mAdapter.setListener(new AdapterGetFollowers.OnItem() {
            @Override
            public void onPosition(int pos) {
                showDialogOrder(pos);
            }
        });

        rvGetFollowers.setItemAnimator(new DefaultItemAnimator());
        linearLayoutManager = new LinearLayoutManager(mContext);
        rvGetFollowers.setLayoutManager(linearLayoutManager);
        rvGetFollowers.setHasFixedSize(false);
        rvGetFollowers.setAdapter(mAdapter);

        populateRecyclerView();

    }

    private void showDialogOrder(int pos) {


    }

    private void populateRecyclerView() {

        mModelGetFollowers = new ModelGetFollowers(100, 10);
        mDataList.add(mModelGetFollowers);

        mModelGetFollowers = new ModelGetFollowers(250, 25);
        mDataList.add(mModelGetFollowers);

        mModelGetFollowers = new ModelGetFollowers(500, 50);
        mDataList.add(mModelGetFollowers);

        mModelGetFollowers = new ModelGetFollowers(1000, 100);
        mDataList.add(mModelGetFollowers);

        mModelGetFollowers = new ModelGetFollowers(2000, 200);
        mDataList.add(mModelGetFollowers);

        mModelGetFollowers = new ModelGetFollowers(3000, 300);
        mDataList.add(mModelGetFollowers);

        mModelGetFollowers = new ModelGetFollowers(5000, 500);
        mDataList.add(mModelGetFollowers);

        mModelGetFollowers = new ModelGetFollowers(10000, 1000);
        mDataList.add(mModelGetFollowers);

        mAdapter.notifyDataSetChanged();

    }

}