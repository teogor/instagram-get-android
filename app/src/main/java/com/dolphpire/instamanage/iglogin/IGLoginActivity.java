package com.dolphpire.instamanage.iglogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.dolphpire.instamanage.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IGLoginActivity extends AppCompatActivity {

    @BindView(R.id.imvBack)
    ImageView imvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ig_login);

        ButterKnife.bind(this);

        imvBack.setOnClickListener(view -> finish());

    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}