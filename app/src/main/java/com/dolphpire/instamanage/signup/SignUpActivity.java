package com.dolphpire.instamanage.signup;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.dolphpire.android.material.textfield.TextInputEditText;
import com.dolphpire.android.material.textfield.TextInputLayout;
import com.dolphpire.api.action.user.check.DataCheckAction;
import com.dolphpire.api.initializer.DolphPireApp;
import com.dolphpire.api.interfaces.ApiCallback;
import com.dolphpire.api.interfaces.FailureCallback;
import com.dolphpire.instamanage.R;
import com.dolphpire.instamanage.login.LoginActivity;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity
{

    @BindView(R.id.tilInputEmail)
    TextInputLayout tilInputEmail;
    @BindView(R.id.tietInputEmail)
    TextInputEditText tietInputEmail;
    @BindView(R.id.tilInputUsername)
    TextInputLayout tilInputUsername;
    @BindView(R.id.tietInputUsername)
    TextInputEditText tietInputUsername;
    @BindView(R.id.tilInputPassword)
    TextInputLayout tilInputPassword;
    @BindView(R.id.tietInputPassword)
    TextInputEditText tietInputPassword;
    @BindView(R.id.tietInputPasswordC)
    TextInputEditText tietInputPasswordC;
    @BindView(R.id.tilInputPasswordC)
    TextInputLayout tilInputPasswordC;
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
        setContentView(R.layout.activity_signup);

        ButterKnife.bind(this);

        rlLoading.setVisibility(View.GONE);

        llLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        tietInputPasswordC.setOnEditorActionListener((v, actionId, event) ->
        {
            if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE))
            {

                tryRegister();

            }
            return false;
        });

        llCreateAccount.setOnClickListener(v ->
        {
            tryRegister();
        });

        DataCheckAction usernameCheck = DolphPireApp.initializeApi().user().check();
        DataCheckAction emailCheck = DolphPireApp.initializeApi().user().check();

        tietInputUsername.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                usernameCheck.username(s.toString())
                        .addOnFoundListener(found ->
                        {
                            if (found)
                            {
                                tilInputUsername.setErrorEnabled(true);
                                tilInputUsername.setError("Username already exists");
                            } else
                            {
                                tilInputUsername.setErrorEnabled(false);
                            }
                        })
                        .execute();
            }

            @Override
            public void afterTextChanged(Editable s)
            {
            }
        });

        tietInputEmail.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                emailCheck.username(s.toString())
                        .addOnFoundListener(found ->
                        {
                            if (found)
                            {
                                tilInputEmail.setErrorEnabled(true);
                                tilInputEmail.setError("Email already exists");
                            } else
                            {
                                tilInputEmail.setErrorEnabled(false);
                            }
                        })
                        .execute();
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });

    }

    private void tryRegister()
    {

        rlLoading.setVisibility(View.VISIBLE);
        if (validateInput())
        {
            DolphPireApp.initializeApi().signup().createAccount()
                    .setPassword(Objects.requireNonNull(tietInputPassword.getText()).toString())
                    .setUsername(Objects.requireNonNull(tietInputUsername.getText()).toString())
                    .setEmail(Objects.requireNonNull(tietInputEmail.getText()).toString())
                    .set()
                    .addOnCompleteListener(() ->
                    {
                        rlLoading.setVisibility(View.GONE);
                        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    })
                    .addOnFailureListener(e ->
                    {
                        rlLoading.setVisibility(View.GONE);
                        Toast.makeText(SignUpActivity.this, "check the data entered and try again", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailedListener(() ->
                    {
                        rlLoading.setVisibility(View.GONE);
                        Toast.makeText(SignUpActivity.this, "check the data entered and try again", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> rlLoading.setVisibility(View.GONE))
                    .execute();
        } else
        {
            rlLoading.setVisibility(View.GONE);
        }

    }

    private boolean validateInput()
    {

        boolean validData = true;

        String email = Objects.requireNonNull(tietInputEmail.getText()).toString().trim();
        String username = Objects.requireNonNull(tietInputUsername.getText()).toString().trim();
        String password = Objects.requireNonNull(tietInputPassword.getText()).toString().trim();
        String passwordC = Objects.requireNonNull(tietInputPasswordC.getText()).toString().trim();

        if (email.isEmpty())
        {

            tilInputEmail.setErrorEnabled(true);
            tilInputEmail.setError("Empty field");
            validData = false;

        } else
        {

            tilInputEmail.setErrorEnabled(false);

        }

        if (username.isEmpty())
        {

            tilInputUsername.setErrorEnabled(true);
            tilInputUsername.setError("Empty field");
            validData = false;

        } else
        {

            tilInputUsername.setErrorEnabled(false);

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

        if (passwordC.isEmpty())
        {

            tilInputPasswordC.setErrorEnabled(true);
            tilInputPasswordC.setError("Empty field");
            validData = false;

        } else
        {

            tilInputPasswordC.setErrorEnabled(false);

        }

        if (!password.isEmpty() && !passwordC.isEmpty() && !password.equals(passwordC))
        {

            tilInputPassword.setErrorEnabled(true);
            tilInputPassword.setError("Passwords don't match");
            tilInputPasswordC.setErrorEnabled(true);
            tilInputPasswordC.setError("Passwords don't match");
            validData = false;

        }

        return validData;

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
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