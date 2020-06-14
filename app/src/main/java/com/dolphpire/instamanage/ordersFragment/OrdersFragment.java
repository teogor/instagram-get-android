package com.dolphpire.instamanage.ordersFragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.dolphpire.api.initializer.DolphPireApp;
import com.dolphpire.api.models.UserModel;
import com.dolphpire.insapi.request.InsRequestCallBack;
import com.dolphpire.insapi.request.api.userinfo.GetUserDetails;
import com.dolphpire.insapi.request.api.userinfo.UserInfoResponseData;
import com.dolphpire.instamanage.R;
import com.dolphpire.instamanage.getCoinsFragment.GetCoinsFragment;
import com.dolphpire.instamanage.getFollowersFragment.GetFollowersFragment;
import com.dolphpire.instamanage.getLikesFragment.GetLikesFragment;
import com.dolphpire.instamanage.igaccounts.IGAccountActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.dolphpire.api.utils.NumberFormat.numberFormat;

public class OrdersFragment extends Fragment
{

    @BindView(R.id.btnInProgress)
    TextView btnInProgress;
    @BindView(R.id.btnCompleted)
    TextView btnCompleted;
    @BindView(R.id.title_toolbar)
    TextView title_toolbar;
    @BindView(R.id.txt_coins)
    TextView txt_coins;
    @BindView(R.id.imvIGUser)
    CircleImageView imvIGUser;
    private View mView;
    private RelativeLayout rlToolbar;
    private Context mContext;
    private Activity mActivity;

    public OrdersFragment()
    {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        mView = inflater.inflate(R.layout.fragment_orders, container, false);
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

        GetCoinsFragment getCoinsFragment = new GetCoinsFragment();
        GetLikesFragment getLikesFragment = new GetLikesFragment();
        GetFollowersFragment getFollowersFragment = new GetFollowersFragment();

        btnInProgress.setOnClickListener(v ->
        {
            showFragment(getLikesFragment);
            btnInProgress.setTextColor(ContextCompat.getColor(mContext, R.color.colorTextLvl1));
            btnCompleted.setTextColor(ContextCompat.getColor(mContext, R.color.colorTextLvl3));
            title_toolbar.setText("In Progress");
        });

        btnCompleted.setOnClickListener(v ->
        {
            showFragment(getFollowersFragment);
            btnInProgress.setTextColor(ContextCompat.getColor(mContext, R.color.colorTextLvl3));
            btnCompleted.setTextColor(ContextCompat.getColor(mContext, R.color.colorTextLvl1));
            title_toolbar.setText("Completed");
        });

        showFragment(getCoinsFragment);

        imvIGUser.setOnClickListener(v ->
        {
            Intent intent = new Intent(mContext, IGAccountActivity.class);
            mContext.startActivity(intent);
        });

        txt_coins.setOnClickListener(v ->
        {

            if (DolphPireApp.getInstance().getIGAccount() != null)
            {
                GetUserDetails mGetUserDetails = new GetUserDetails(DolphPireApp.getInstance().getIGAccount().getUsername());
                mGetUserDetails.execute(new InsRequestCallBack<UserInfoResponseData>()
                {
                    @Override
                    public void onSuccess(int statusCode, UserInfoResponseData insBaseData)
                    {

                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg)
                    {

                    }
                });
            }
        });

        txt_coins.setText(numberFormat(DolphPireApp.getInstance().getUser().getCoins()));
        DolphPireApp.getInstance().syncUser()
                .setListener(this::onSyncUser, "ORDERS_FRAGMENT");

        setIGAccountData();
        DolphPireApp.getInstance().syncIGAccount()
                .setListener(user -> setIGAccountData(), "ORDERS_FRAGMENT");

    }

    private void setIGAccountData()
    {
        if (DolphPireApp.getInstance().getIGAccount() != null)
        {
            Glide.with(this)
                    .load(DolphPireApp.getInstance().getIGAccount().getProfilePicture())
                    .into(imvIGUser);
        }
    }

    private void onSyncUser(UserModel user)
    {
        txt_coins.setText(numberFormat(user.getCoins()));
    }

    private void showFragment(Fragment fragment)
    {
        getChildFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

}