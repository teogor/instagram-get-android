package com.dolphpire.instamanage.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.dolphpire.android.material.textfield.TextInputEditText;
import com.dolphpire.android.material.textfield.TextInputLayout;
import com.dolphpire.api.initializer.DolphPireApp;
import com.dolphpire.api.interfaces.ZFlowLoginListener;
import com.dolphpire.instamanage.R;
import com.dolphpire.instamanage.home.HomeActivity;
import com.dolphpire.instamanage.signup.SignUpActivity;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.dolphpire.instamanage.utils.Utils.hideKeyboard;

public class LoginActivity extends AppCompatActivity
{

    @BindView(R.id.tilInputLogIn)
    TextInputLayout tilInputLogIn;
    @BindView(R.id.tietInputLogIn)
    TextInputEditText tietInputLogIn;
    @BindView(R.id.tilInputPassword)
    TextInputLayout tilInputPassword;
    @BindView(R.id.tietInputPassword)
    TextInputEditText tietInputPassword;
    @BindView(R.id.llLogin)
    LinearLayout llLogin;
    @BindView(R.id.llCreateAccount)
    LinearLayout llCreateAccount;
    @BindView(R.id.rlLoading)
    RelativeLayout rlLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        rlLoading.setVisibility(View.GONE);

        llLogin.setOnClickListener(v -> tryLogin());

        tietInputPassword.setOnEditorActionListener((v, actionId, event) ->
        {
            if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE))
            {

                tryLogin();

            }
            return false;
        });

        llCreateAccount.setOnClickListener(v ->
        {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
            finish();
        });

    }

    private void tryLogin()
    {
        rlLoading.setVisibility(View.VISIBLE);
        hideKeyboard(LoginActivity.this);
        if (validateInput())
        {
            login();
        } else
        {
            rlLoading.setVisibility(View.GONE);
        }
    }

    private boolean validateInput()
    {

        boolean validData = true;

        String logKey = Objects.requireNonNull(tietInputLogIn.getText()).toString().trim();
        String password = Objects.requireNonNull(tietInputPassword.getText()).toString().trim();

        if (logKey.isEmpty())
        {

            tilInputLogIn.setErrorEnabled(true);
            tilInputLogIn.setError("Empty field");
            validData = false;

        } else
        {

            tilInputLogIn.setErrorEnabled(false);

        }

        if (password.isEmpty())
        {

            tilInputPassword.setErrorEnabled(true);
            tilInputPassword.setError("Empty field");
            validData = false;

        } else
        {

            tilInputPassword.setErrorEnabled(false);

        }

        return validData;

    }

    private void login()
    {
        DolphPireApp.initializeApi()
                .login()
                .withLoginKey(Objects.requireNonNull(tietInputLogIn.getText()).toString())
                .withPassword(Objects.requireNonNull(tietInputPassword.getText()).toString())
                .set()
                .addOnLoggedInListener(userData ->
                {
                    if (DolphPireApp.getInstance().getUser().getIGAccounts() != null)
                    {
                        DolphPireApp.getInstance().setCurrentAccount(DolphPireApp.getInstance().getUser().getIGAccounts().get(0));
                    }
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                })
                .addOnFailureListener(new ZFlowLoginListener.OnLoginFailure()
                {
                    @Override
                    public void onAccountClosed(@NonNull String error)
                    {

                    }

                    @Override
                    public void onEmailNotVerified(@NonNull String error)
                    {

                    }

                    @Override
                    public void onTwoStepsAuth(@NonNull String error)
                    {

                    }

                    @Override
                    public void onBadLogKey(@NonNull String error)
                    {

                        tilInputLogIn.setErrorEnabled(true);
                        tilInputLogIn.setError(error);
                        rlLoading.setVisibility(View.GONE);

                    }

                    @Override
                    public void onBadPassword(@NonNull String error)
                    {

                        tilInputPassword.setErrorEnabled(true);
                        tilInputPassword.setError(error);
                        rlLoading.setVisibility(View.GONE);

                    }
                })
                .addOnFailureListener(e -> rlLoading.setVisibility(View.GONE))
                .execute();

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();
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