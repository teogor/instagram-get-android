package com.dolphpire.instamanage.getlikesfragment;

import android.animation.ArgbEvaluator;
import android.animation.TimeAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.dolphpire.api.initializer.DolphPireApp;
import com.dolphpire.instamanage.R;
import com.dolphpire.instamanage.getlikesfragment.adapter.AdapterGetLikes;
import com.dolphpire.instamanage.getlikesfragment.model.ModelGetLikes;
import com.dolphpire.instamanage.igaccounts.IGAccountActivity;
import com.dolphpire.instamanage.igposts.IGPostsActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GetLikesFragment extends Fragment {

    @BindView(R.id.rvGetLikes)
    RecyclerView rvGetLikes;
    @BindView(R.id.llBottomPlaceOrder)
    LinearLayout llBottomPlaceOrder;
    @BindView(R.id.llCancelOrder)
    LinearLayout llCancelOrder;
    @BindView(R.id.llPlaceOrder)
    LinearLayout llPlaceOrder;
    @BindView(R.id.txtAmountCoins)
    TextView txtAmountCoins;
    @BindView(R.id.txtAmountLikes)
    TextView txtAmountLikes;
    @BindView(R.id.rlPostHolder)
    RelativeLayout rlPostHolder;
    @BindView(R.id.srlRefreshLikes)
    SwipeRefreshLayout srlRefreshLikes;
    private View mView;
    private Context mContext;
    private Activity mActivity;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<ModelGetLikes> mDataList;
    private AdapterGetLikes mAdapter;
    private ModelGetLikes mModelGetLikes;
    private int itemChose = -1;

    public GetLikesFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_get_likes, container, false);
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
        mAdapter = new AdapterGetLikes(mDataList, mActivity);
        mAdapter.setListener(this::showDialogOrder);

        rvGetLikes.setItemAnimator(new DefaultItemAnimator());
        linearLayoutManager = new LinearLayoutManager(mContext);
        rvGetLikes.setLayoutManager(linearLayoutManager);
        rvGetLikes.setHasFixedSize(false);
        rvGetLikes.setAdapter(mAdapter);

        llBottomPlaceOrder.setVisibility(View.GONE);

        setAnimation();

        populateRecyclerView();

        llCancelOrder.setOnClickListener(v -> llBottomPlaceOrder.setVisibility(View.GONE));

        llPlaceOrder.setOnClickListener(v ->
        {

            DolphPireApp.initializeApi().igAccount().posts()
                    .withUserID(DolphPireApp.getInstance().getIGAccount().getUsername())
                    .set()
                    .execute();
            llBottomPlaceOrder.setVisibility(View.GONE);
            DolphPireApp.initializeApi()
                    .user().order()
                    .likes(DolphPireApp.getInstance().getIGAccount().getIGID(), itemChose)
                    .execute();
            Toast.makeText(mContext, "Purchased " + mDataList.get(itemChose).getLikes() + " likes", Toast.LENGTH_SHORT).show();
        });

        rlPostHolder.setOnClickListener(v ->
        {
            Intent intent = new Intent(mContext, IGPostsActivity.class);
            mContext.startActivity(intent);
        });

        srlRefreshLikes.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                srlRefreshLikes.setRefreshing(false);
            }
        });

    }

    private void setAnimation() {

        final ArgbEvaluator evaluator = new ArgbEvaluator();
        final int start = ContextCompat.getColor(mContext, R.color.colorBgPlaceOrder1);
        final int end = ContextCompat.getColor(mContext, R.color.colorBgPlaceOrder2);
        final GradientDrawable gradient = (GradientDrawable) llBottomPlaceOrder.getBackground();

        ValueAnimator animator = TimeAnimator.ofFloat(0.0f, 1.0f);
        animator.setDuration(1500);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.addUpdateListener(valueAnimator -> {
            float fraction = valueAnimator.getAnimatedFraction();
            int newStrat = (int) evaluator.evaluate(fraction, start, end);
            int newEnd = (int) evaluator.evaluate(fraction, end, start);
            int[] newArray = {newStrat, newEnd};
            gradient.setColors(newArray);
        });

        animator.start();

    }

    private void showDialogOrder(int pos) {

        itemChose = pos;

        txtAmountCoins.setText(String.valueOf(mDataList.get(pos).getCoins()));
        txtAmountLikes.setText(String.valueOf(mDataList.get(pos).getLikes()));

        llBottomPlaceOrder.setVisibility(View.VISIBLE);

    }

    private void populateRecyclerView() {

        mModelGetLikes = new ModelGetLikes(20, 10);
        mDataList.add(mModelGetLikes);

        mModelGetLikes = new ModelGetLikes(50, 25);
        mDataList.add(mModelGetLikes);

        mModelGetLikes = new ModelGetLikes(100, 50);
        mDataList.add(mModelGetLikes);

        mModelGetLikes = new ModelGetLikes(200, 100);
        mDataList.add(mModelGetLikes);

        mModelGetLikes = new ModelGetLikes(500, 250);
        mDataList.add(mModelGetLikes);

        mModelGetLikes = new ModelGetLikes(1000, 500);
        mDataList.add(mModelGetLikes);

        mModelGetLikes = new ModelGetLikes(2000, 1000);
        mDataList.add(mModelGetLikes);

        mModelGetLikes = new ModelGetLikes(4000, 2000);
        mDataList.add(mModelGetLikes);

        mModelGetLikes = new ModelGetLikes(10000, 5000);
        mDataList.add(mModelGetLikes);

        mAdapter.notifyDataSetChanged();

    }

}