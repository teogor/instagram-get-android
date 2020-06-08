package com.dolphpire.instamanage.home;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dolphpire.api.initializer.DolphPireApp;
import com.dolphpire.instamanage.R;
import com.dolphpire.instamanage.home.drawer.DrawerAdapter;
import com.dolphpire.instamanage.home.drawer.DrawerItem;
import com.dolphpire.instamanage.home.drawer.SimpleItem;
import com.dolphpire.instamanage.home.drawer.SpaceItem;
import com.dolphpire.instamanage.homeFragment.HomeFragment;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.Arrays;

import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity
{

    CircleImageView igUserImage;
    TextView txt_username;

    private HomeFragment mHomeFragment = new HomeFragment();
    private int POS_HOME = 0;
    private int POS_ACCOUNT = 1;
    private int POS_LOG_OUT = 3;
    private String[] screenTitles;
    private Drawable[] screenIcons;
    private SlidingRootNav slidingRootNav;
    private DrawerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        setContentView(R.layout.activity_home);
        setDrawer(savedInstanceState);

        setIGAccount();

    }

    private void setIGAccount()
    {
        igUserImage = findViewById(R.id.igUserImage);
        txt_username = findViewById(R.id.txt_username);
        if (DolphPireApp.getInstance().getIGAccount() != null)
        {
            Glide.with(this)
                    .load(DolphPireApp.getInstance().getIGAccount().getProfilePicture())
                    .into(igUserImage);
            txt_username.setText(DolphPireApp.getInstance().getIGAccount().getUsername());
        }

        DolphPireApp.getInstance().syncIGAccount()
                .setListener(user -> setIGAccountData(), "IG_ACCOUNT_ACTIVITY");
    }

    private void setIGAccountData()
    {

        if (DolphPireApp.getInstance().getIGAccount() != null)
        {
            Glide.with(this)
                    .load(DolphPireApp.getInstance().getIGAccount().getProfilePicture())
                    .into(igUserImage);
            txt_username.setText(DolphPireApp.getInstance().getIGAccount().getUsername());
        }
    }

    private void setDrawer(Bundle savedInstanceState)
    {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        slidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.menu_home_drawer)
                .inject();

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        adapter = new DrawerAdapter(Arrays.asList(
                createItemFor(POS_HOME).setChecked(true),
                createItemFor(POS_ACCOUNT),
                new SpaceItem(14),
                createItemFor(POS_LOG_OUT)));

        adapter.setListener(position ->
        {

            slidingRootNav.closeMenu(true);

            if (position == POS_HOME)
            {

                showFragment(mHomeFragment);

            }

        });

        RecyclerView list = findViewById(R.id.list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);
        adapter.setSelected(0);

    }

    private void showFragment(Fragment fragment)
    {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    private DrawerItem createItemFor(int position)
    {
        return new SimpleItem(screenIcons[position], screenTitles[position])
                .withIconTint(color(R.color.colorUnselected))
                .withTextTint(color(R.color.colorUnselected))
                .withTextSize(14)
                .withIconSize(40)
                .withSelectedIconTint(color(R.color.colorSelected))
                .withSelectedTextTint(color(R.color.colorSelected))
                .withSelectedTextSize(16)
                .withSelectedIconSize(48);
    }

    private String[] loadScreenTitles()
    {
        return getResources().getStringArray(R.array.ld_activityScreenTitles);
    }

    private Drawable[] loadScreenIcons()
    {
        TypedArray ta = getResources().obtainTypedArray(R.array.ld_activityScreenIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++)
        {
            int id = ta.getResourceId(i, 0);
            if (id != 0)
            {
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;
    }

    @ColorInt
    private int color(@ColorRes int res)
    {
        return ContextCompat.getColor(this, res);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

}