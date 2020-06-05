package com.dolphpire.instamanage.getcoinsfragment;

import android.animation.ArgbEvaluator;
import android.animation.TimeAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.dolphpire.instamanage.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GetCoinsFragment extends Fragment {

    @BindView(R.id.rlBottomControls)
    RelativeLayout rlBottomControls;
    private View mView;
    private Context mContext;
    private Activity mActivity;

    public GetCoinsFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_get_coins, container, false);
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

        setAnimation();

        // 1 coins / like
        // 4 coins / follow

    }

    private void setAnimation() {

        final ArgbEvaluator evaluator = new ArgbEvaluator();
        final int start = ContextCompat.getColor(mContext, R.color.colorBgGetCoins1);
        final int end = ContextCompat.getColor(mContext, R.color.colorBgGetCoins2);
        final GradientDrawable gradient = (GradientDrawable) rlBottomControls.getBackground();

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

}