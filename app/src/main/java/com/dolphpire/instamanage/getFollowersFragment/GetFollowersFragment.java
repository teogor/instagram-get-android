package com.dolphpire.instamanage.getFollowersFragment;

import android.animation.ArgbEvaluator;
import android.animation.TimeAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
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

import com.bumptech.glide.Glide;
import com.dolphpire.api.initializer.DolphPireApp;
import com.dolphpire.api.interfaces.DPireOnCompleteCallback;
import com.dolphpire.instamanage.R;
import com.dolphpire.instamanage.getFollowersFragment.adapter.AdapterGetFollowers;
import com.dolphpire.instamanage.getFollowersFragment.model.ModelGetFollowers;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class GetFollowersFragment extends Fragment
{

    @BindView(R.id.rvGetFollowers)
    RecyclerView rvGetFollowers;
    @BindView(R.id.llBottomPlaceOrder)
    LinearLayout llBottomPlaceOrder;
    @BindView(R.id.llCancelOrder)
    LinearLayout llCancelOrder;
    @BindView(R.id.llPlaceOrder)
    LinearLayout llPlaceOrder;
    @BindView(R.id.llBottomLoading)
    RelativeLayout llBottomLoading;
    @BindView(R.id.txtAmountCoins)
    TextView txtAmountCoins;
    @BindView(R.id.txtAmountFollowers)
    TextView txtAmountFollowers;
    @BindView(R.id.imvIGUser)
    CircleImageView imvIGUser;
    @BindView(R.id.txt_username)
    TextView txt_username;
    @BindView(R.id.txt_followers)
    TextView txt_followers;
    @BindView(R.id.srlRefreshFollowers)
    SwipeRefreshLayout srlRefreshFollowers;
    private View mView;
    private Context mContext;
    private Activity mActivity;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<ModelGetFollowers> mDataList;
    private AdapterGetFollowers mAdapter;
    private ModelGetFollowers mModelGetFollowers;
    private int itemChose = -1;

    public GetFollowersFragment()
    {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        mView = inflater.inflate(R.layout.fragment_get_followers, container, false);
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
        mAdapter = new AdapterGetFollowers(mDataList, mActivity);
        mAdapter.setListener(this::showDialogOrder);

        rvGetFollowers.setItemAnimator(new DefaultItemAnimator());
        linearLayoutManager = new LinearLayoutManager(mContext);
        rvGetFollowers.setLayoutManager(linearLayoutManager);
        rvGetFollowers.setHasFixedSize(false);
        rvGetFollowers.setAdapter(mAdapter);

        llBottomPlaceOrder.setVisibility(View.GONE);
        llBottomLoading.setVisibility(View.GONE);

        setAnimation();

        populateRecyclerView();

        llCancelOrder.setOnClickListener(v -> llBottomPlaceOrder.setVisibility(View.GONE));

        llPlaceOrder.setOnClickListener(v ->
        {
            llBottomPlaceOrder.setVisibility(View.GONE);
            llBottomLoading.setVisibility(View.VISIBLE);
            if (DolphPireApp.getInstance().getUser().getCoins() >= mDataList.get(itemChose).getCoins())
            {
                DolphPireApp.initializeApi()
                        .user().order()
                        .followers(
                                DolphPireApp.getInstance().getIGAccount().getIGID(),
                                itemChose
                        )
                        .addOnCompleteListener(() ->
                        {
                            llBottomLoading.setVisibility(View.GONE);
                            Toast.makeText(mContext, "Purchased " + mDataList.get(itemChose).getFollowers() + " followers", Toast.LENGTH_SHORT).show();
                        })
                        .addOnFailureListener(e ->
                        {
                            llBottomPlaceOrder.setVisibility(View.VISIBLE);
                            llBottomLoading.setVisibility(View.GONE);
                        })
                        .execute();
                DolphPireApp.getInstance().decreaseCoinsBy(mDataList.get(itemChose).getCoins());
            } else
            {
                Toast.makeText(mContext, "Failed to purchase. You don't have enough coins.", Toast.LENGTH_SHORT).show();
                llBottomLoading.setVisibility(View.GONE);
            }
        });

        setIGAccount();

        getFollowersCount();
        srlRefreshFollowers.setOnRefreshListener(() ->
        {
            getFollowersCount();
            srlRefreshFollowers.setRefreshing(false);
        });

    }

    private void getFollowersCount()
    {

        DolphPireApp.initializeApi()
                .igAccount().followersCount()
                .withUsername(DolphPireApp.getInstance().getIGAccount().getUsername())
                .set()
                .addOnCompleteListener(new DPireOnCompleteCallback.OnComplete()
                {
                    @Override
                    public void onCompleted()
                    {

                    }
                })
                .execute();
    }

    private void setIGAccount()
    {
        if (DolphPireApp.getInstance().getIGAccount() != null)
        {
            Glide.with(this)
                    .load(DolphPireApp.getInstance().getIGAccount().getProfilePicture())
                    .into(imvIGUser);
            txt_username.setText("@" + DolphPireApp.getInstance().getIGAccount().getUsername());
        }

        DolphPireApp.getInstance().syncIGAccount()
                .setListener(user -> setIGAccountData(), "IG_GET_FOLLOWERS_FRAGMENT");
    }

    @SuppressLint("SetTextI18n")
    private void setIGAccountData()
    {
        if (DolphPireApp.getInstance().getIGAccount() != null)
        {
            Glide.with(mActivity)
                    .load(DolphPireApp.getInstance().getIGAccount().getProfilePicture())
                    .into(imvIGUser);
            txt_username.setText("@" + DolphPireApp.getInstance().getIGAccount().getUsername());
        }
    }

    private void setAnimation()
    {

        final ArgbEvaluator evaluator = new ArgbEvaluator();
        final int start = ContextCompat.getColor(mContext, R.color.colorBgPlaceOrder1);
        final int end = ContextCompat.getColor(mContext, R.color.colorBgPlaceOrder2);
        final GradientDrawable gradientPO = (GradientDrawable) llBottomPlaceOrder.getBackground();
        final GradientDrawable gradientL = (GradientDrawable) llBottomLoading.getBackground();

        ValueAnimator animator = TimeAnimator.ofFloat(0.0f, 1.0f);
        animator.setDuration(1500);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.addUpdateListener(valueAnimator ->
        {
            float fraction = valueAnimator.getAnimatedFraction();
            int newStrat = (int) evaluator.evaluate(fraction, start, end);
            int newEnd = (int) evaluator.evaluate(fraction, end, start);
            int[] newArray = {newStrat, newEnd};
            gradientPO.setColors(newArray);
            gradientL.setColors(newArray);
        });

        animator.start();

    }

    private void showDialogOrder(int pos)
    {

        itemChose = pos;

        txtAmountCoins.setText(String.valueOf(mDataList.get(pos).getCoins()));
        txtAmountFollowers.setText(String.valueOf(mDataList.get(pos).getFollowers()));

        llBottomPlaceOrder.setVisibility(View.VISIBLE);

    }

    private void populateRecyclerView()
    {

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