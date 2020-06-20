package com.dolphpire.instamanage.getCoinsFragment;

import android.animation.ArgbEvaluator;
import android.animation.TimeAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.dolphpire.api.initializer.DolphPireApp;
import com.dolphpire.api.interfaces.OnFeedRetrieved;
import com.dolphpire.api.models.FeedModel;
import com.dolphpire.instamanage.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.MODE_PRIVATE;
import static com.dolphpire.api.utils.DolphPireUtils.DPIRE_SP_APP_DATA;
import static com.dolphpire.api.utils.DolphPireUtils.DPIRE_SP_FILTER_FOLLOW;
import static com.dolphpire.api.utils.DolphPireUtils.DPIRE_SP_FILTER_LIKE;

public class GetCoinsFragment extends Fragment {

    @BindView(R.id.rlBottomControls)
    RelativeLayout rlBottomControls;
    @BindView(R.id.rlControlsHolder)
    RelativeLayout rlControlsHolder;
    @BindView(R.id.rlFilterMenu)
    RelativeLayout rlFilterMenu;
    @BindView(R.id.txtApplyFilters)
    TextView txtApplyFilters;
    @BindView(R.id.llAuto)
    LinearLayout llAuto;
    @BindView(R.id.llActionTask)
    LinearLayout llActionTask;
    @BindView(R.id.llSkip)
    LinearLayout llSkip;
    @BindView(R.id.llFilters)
    RelativeLayout llFilters;
    @BindView(R.id.txtAutoAction)
    TextView txtAutoAction;
    @BindView(R.id.txtAmountCoins)
    TextView txtAmountCoins;
    @BindView(R.id.cbLike)
    CheckBox cbLike;
    @BindView(R.id.cbFollow)
    CheckBox cbFollow;
    private View mView;
    private Context mContext;
    private Activity mActivity;
    private boolean autoActionOn = false;
    private boolean filterLikeTasks = true;
    private boolean filterFollowTasks = true;

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

        SharedPreferences dpireFilterOptionsSP = mContext.getSharedPreferences(DPIRE_SP_APP_DATA, MODE_PRIVATE);
        filterFollowTasks = dpireFilterOptionsSP.getBoolean(DPIRE_SP_FILTER_FOLLOW, true);
        filterLikeTasks = dpireFilterOptionsSP.getBoolean(DPIRE_SP_FILTER_LIKE, true);

        cbFollow.setChecked(filterFollowTasks);
        cbLike.setChecked(filterLikeTasks);
        if (filterFollowTasks && filterLikeTasks) {
            txtAutoAction.setText("Auto Follow & Like");
        } else if (filterLikeTasks) {
            txtAutoAction.setText("Auto Like");
        } else if (filterFollowTasks) {
            txtAutoAction.setText("Auto Follow");
        }

        // 1 coins / like
        // 4 coins / follow

        llActionTask.setOnClickListener(v -> {
            if (autoActionOn) {
                Toast.makeText(mContext, "Disable 'auto' option first", Toast.LENGTH_SHORT).show();
            } else {

            }
        });

        llSkip.setOnClickListener(v -> {
            if (autoActionOn) {
                Toast.makeText(mContext, "Disable 'auto' option first", Toast.LENGTH_SHORT).show();
            } else {
                getFeed();
            }
        });

        llAuto.setOnClickListener(v -> toggleAutoAction());

        llFilters.setOnClickListener(v -> {
            if (autoActionOn) {
                Toast.makeText(mContext, "Disable 'auto' option first", Toast.LENGTH_SHORT).show();
            } else {
                if (rlFilterMenu.isShown()) {
                    rlFilterMenu.setVisibility(View.GONE);
                    rlControlsHolder.setVisibility(View.VISIBLE);
                } else {
                    rlFilterMenu.setVisibility(View.VISIBLE);
                    rlControlsHolder.setVisibility(View.GONE);
                }
            }
        });

        txtApplyFilters.setOnClickListener(v -> {
            if (!cbLike.isChecked() && !cbFollow.isChecked()) {
                Toast.makeText(mContext, "You can not disable all the filters", Toast.LENGTH_SHORT).show();
            } else if (rlFilterMenu.isShown()) {
                rlFilterMenu.setVisibility(View.GONE);
                rlControlsHolder.setVisibility(View.VISIBLE);

                SharedPreferences.Editor dpireFilterOptionsSPEdit = mContext.getSharedPreferences(DPIRE_SP_APP_DATA, MODE_PRIVATE).edit();
                dpireFilterOptionsSPEdit.putBoolean(DPIRE_SP_FILTER_FOLLOW, cbFollow.isChecked());
                dpireFilterOptionsSPEdit.putBoolean(DPIRE_SP_FILTER_LIKE, cbLike.isChecked());
                dpireFilterOptionsSPEdit.apply();

                filterFollowTasks = cbFollow.isChecked();
                filterLikeTasks = cbLike.isChecked();
                if (filterFollowTasks && filterLikeTasks) {
                    txtAutoAction.setText("Auto Follow & Like");
                } else if (filterLikeTasks) {
                    txtAutoAction.setText("Auto Like");
                } else if (filterFollowTasks) {
                    txtAutoAction.setText("Auto Follow");
                }
            } else {
                rlFilterMenu.setVisibility(View.VISIBLE);
                rlControlsHolder.setVisibility(View.GONE);
            }

        });

        rlFilterMenu.setVisibility(View.GONE);

        getFeed();

    }

    private void getFeed()
    {

        DolphPireApp.initializeApi()
                .orders().feed()
                .all()
                .addOnCompleteListener(new OnFeedRetrieved.OnCompleteListener<FeedModel>()
                {
                    @Override
                    public void onSuccess(@NonNull List<FeedModel> dataList)
                    {
                        Toast.makeText(mContext, String.valueOf(dataList.size()), Toast.LENGTH_SHORT).show();
                    }
                })
                .execute();

    }

    private void toggleAutoAction() {

        autoActionOn = !autoActionOn;
        if (autoActionOn) {
            txtAutoAction.setTextColor(Color.parseColor("#AC1005"));
        } else {
            txtAutoAction.setTextColor(Color.parseColor("#000000"));
        }

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