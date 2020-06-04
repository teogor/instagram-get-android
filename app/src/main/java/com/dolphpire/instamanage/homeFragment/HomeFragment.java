package com.dolphpire.instamanage.homeFragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.dolphpire.instamanage.R;
import com.dolphpire.instamanage.getcoinsfragment.GetCoinsFragment;
import com.dolphpire.instamanage.getfollowersfragment.GetFollowersFragment;
import com.dolphpire.instamanage.getlikesfragment.GetLikesFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {

    private View mView;
    private RelativeLayout rlToolbar;
    private Context mContext;
    private Activity mActivity;

    @BindView(R.id.btnLikes)
    TextView btnLikes;
    @BindView(R.id.btnFollowers)
    TextView btnFollowers;
    @BindView(R.id.btnCoins)
    ImageView btnCoins;

    public HomeFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);
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

        GetCoinsFragment getCoinsFragment = new GetCoinsFragment();
        GetLikesFragment getLikesFragment = new GetLikesFragment();
        GetFollowersFragment getFollowersFragment = new GetFollowersFragment();

        btnLikes.setOnClickListener(v -> showFragment(getLikesFragment));

        btnFollowers.setOnClickListener(v -> showFragment(getFollowersFragment));

        btnCoins.setOnClickListener(v -> showFragment(getCoinsFragment));

        showFragment(getCoinsFragment);

    }

    private void showFragment(Fragment fragment) {
        getChildFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

}