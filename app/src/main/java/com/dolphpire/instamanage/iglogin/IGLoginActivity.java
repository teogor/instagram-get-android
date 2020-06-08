package com.dolphpire.instamanage.iglogin;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.dolphpire.android.material.textfield.TextInputEditText;
import com.dolphpire.android.material.textfield.TextInputLayout;
import com.dolphpire.insapi.manager.IGCommonFieldsManager;
import com.dolphpire.insapi.request.InsRequestCallBack;
import com.dolphpire.insapi.request.api.follower.FollowersResponseData;
import com.dolphpire.insapi.request.api.follower.GetFollowersRequest;
import com.dolphpire.insapi.request.api.header.GetHeaderRequest;
import com.dolphpire.insapi.request.api.login.LoginRequest;
import com.dolphpire.insapi.request.api.login.LoginResponseData;
import com.dolphpire.insapi.response.InsBaseResponseData;
import com.dolphpire.instamanage.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.dolphpire.instamanage.utils.Utils.hideKeyboard;

public class IGLoginActivity extends AppCompatActivity
{

    @BindView(R.id.imvBack)
    ImageView imvBack;
    @BindView(R.id.llLoginHolder)
    LinearLayout llLoginHolder;
    @BindView(R.id.llTermsPolicy)
    LinearLayout llTermsPolicy;
    @BindView(R.id.llLoadingHolder)
    RelativeLayout llLoadingHolder;
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
    FollowersResponseData mFollowersResponseData = new FollowersResponseData();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ig_login);

        getFollowers(true, "");

        ButterKnife.bind(this);

        imvBack.setOnClickListener(view -> finish());

        tietPassword.setOnEditorActionListener((v, actionId, event) ->
        {
            if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE))
            {

                tryLogin();

            }
            return false;
        });

        llLoginButton.setOnClickListener(v -> tryLogin());

        llLoadingHolder.setVisibility(View.GONE);

    }

    private void tryLogin()
    {

        hideKeyboard(IGLoginActivity.this);
        llLoadingHolder.setVisibility(View.VISIBLE);
        llLoginHolder.setVisibility(View.GONE);
        llTermsPolicy.setVisibility(View.GONE);
        if (validateInput())
        {
            String inputData = Objects.requireNonNull(tietInputLogIn.getText()).toString().trim();
            String password = Objects.requireNonNull(tietPassword.getText()).toString().trim();

            getCsftoken();

            LoginRequest loginRequest = new LoginRequest(inputData, password);
            loginRequest.execute(new InsRequestCallBack<LoginResponseData>()
            {
                @Override
                public void onSuccess(int statusCode, LoginResponseData insBaseData)
                {
                    LoginResponseData.LoggedInUserBean loggedInUserBean = null;
                    if (insBaseData != null && (loggedInUserBean = insBaseData.getLogged_in_user()) != null)
                    {
                        String pkId = loggedInUserBean.getPk() + "";
                        if (!TextUtils.isEmpty(pkId))
                        {
                            IGCommonFieldsManager.getInstance().savePKID(pkId);
                        }
                        Log.d("login", "pkId = " + pkId);
                    }
                    llLoadingHolder.setVisibility(View.GONE);
                    llLoginHolder.setVisibility(View.VISIBLE);
                    llTermsPolicy.setVisibility(View.VISIBLE);
                }

                @Override
                public void onFailure(int errorCode, String errorMsg)
                {
                    if (errorMsg.contains("username"))
                    {
                        tilInputLogIn.setErrorEnabled(true);
                        tilInputLogIn.setError("Bad login key");
                    } else if (errorMsg.contains("password"))
                    {
                        tilPassword.setErrorEnabled(true);
                        tilPassword.setError("Bad password");
                    }
                    llLoadingHolder.setVisibility(View.GONE);
                    llLoginHolder.setVisibility(View.VISIBLE);
                    llTermsPolicy.setVisibility(View.VISIBLE);
                }
            });

        } else
        {
            llLoadingHolder.setVisibility(View.GONE);
            llLoginHolder.setVisibility(View.VISIBLE);
            llTermsPolicy.setVisibility(View.VISIBLE);
        }

    }

    private void getFollowers(boolean isFirstPage, String nextMaxId)
    {

        if (isFirstPage)
        {
            mFollowersResponseData.getUsers().clear();
        }
        String userId = IGCommonFieldsManager.getInstance().getPKID();
        GetFollowersRequest getFollowersRequest = new GetFollowersRequest(isFirstPage, userId,
                nextMaxId);
        getFollowersRequest.execute(new InsRequestCallBack<FollowersResponseData>()
        {
            @Override
            public void onSuccess(int statusCode, FollowersResponseData response)
            {
                mFollowersResponseData.setBig_list(response.isBig_list());
                mFollowersResponseData.setNext_max_id(response.getNext_max_id());
                mFollowersResponseData
                        .setPage_size(mFollowersResponseData.getPage_size() + response.getPage_size());
                mFollowersResponseData.getUsers().addAll(response.getUsers());

                if (!TextUtils.isEmpty(response.getNext_max_id()))
                {
                    getFollowers(false, mFollowersResponseData.getNext_max_id());
                }

            }

            @Override
            public void onFailure(int errorCode, String errorMsg)
            {

            }
        });


    }

    private boolean validateInput()
    {

        boolean validData = true;

        String inputData = Objects.requireNonNull(tietInputLogIn.getText()).toString().trim();
        String password = Objects.requireNonNull(tietPassword.getText()).toString().trim();

        if (inputData.isEmpty())
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

            tilPassword.setErrorEnabled(true);
            tilPassword.setError("Empty field");
            validData = false;

        } else
        {

            tilPassword.setErrorEnabled(false);

        }

        return validData;

    }

    private void getCsftoken()
    {
        final GetHeaderRequest getHeaderRequest = new GetHeaderRequest();
        getHeaderRequest.execute(new InsRequestCallBack()
        {
            @Override
            public void onSuccess(int statusCode, InsBaseResponseData insBaseData)
            {
                String csrfCookie = getHeaderRequest.getCsrfCookie();
                Log.d("succeed", "get Csftoken onSuccess，csrfCookie =  " + csrfCookie);
            }

            @Override
            public void onFailure(int errorCode, String errorMsg)
            {
                Log.d("error", "get Csftoken onFailure ");
            }
        });

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

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }

}