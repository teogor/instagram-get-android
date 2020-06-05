package com.dolphpire.instamanage.igaccounts;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;

import com.dolphpire.api.initializer.DolphPireApp;
import com.dolphpire.instamanage.R;

import java.util.Objects;

public class DialogIGAccounts {

    public DialogIGAccounts() {

    }

    @SuppressLint("SetTextI18n")
    public void show() {
        Dialog quickUserLayout = new Dialog(DolphPireApp.getInstance().getApplicationContext());

        View quickUserProfile = View.inflate(DolphPireApp.getInstance().getApplicationContext(), R.layout.dialog_ig_accounts, null);

        quickUserLayout.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(quickUserLayout.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        quickUserLayout.setContentView(quickUserProfile);
        quickUserLayout.show();

    }

}
