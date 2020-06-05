package com.dolphpire.instamanage.iglogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dolphpire.instamanage.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.dolphpire.instamanage.utils.Utils.hideKeyboard;

public class IGLoginActivity extends AppCompatActivity {

    @BindView(R.id.imvBack)
    ImageView imvBack;
    @BindView(R.id.llLoginButton)
    LinearLayout llLoginButton;
    @BindView(R.id.tilInputLogIn)
    TextInputLayout tilInputLogIn;
    @BindView(R.id.tietInputLogIn)
    TextInputEditText tietInputLogIn;
    @BindView(R.id.tilPassword)
    TextInputLayout tilPassword;
    @BindView(R.id.tietPassword)
    TextInputEditText tietPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ig_login);

        ButterKnife.bind(this);

        imvBack.setOnClickListener(view -> finish());

        tietPassword.setOnEditorActionListener((v, actionId, event) -> {
            if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {

                tryLogin();

            }
            return false;
        });

        llLoginButton.setOnClickListener(v -> tryLogin());

    }

    private void tryLogin() {

        hideKeyboard(IGLoginActivity.this);
        if(validateInput()) {

        }

    }

    private boolean validateInput() {

        boolean validData = true;

        String inputData = Objects.requireNonNull(tietInputLogIn.getText()).toString().trim();
        String password = Objects.requireNonNull(tietPassword.getText()).toString().trim();

        if (inputData.isEmpty()) {

            tilInputLogIn.setErrorEnabled(true);
            tilInputLogIn.setError("Empty field");
            validData = false;

        } else {

            tilInputLogIn.setErrorEnabled(false);
            validData = true;

        }

        if (password.isEmpty()) {

            tilPassword.setErrorEnabled(true);
            tilPassword.setError("Empty field");
            validData = false;

        } else {

            tilPassword.setErrorEnabled(false);
            validData = true;


        }

        return validData;

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