package com.dolphpire.api.action.signup;

import com.dolphpire.api.action.signup.account.CreateAccountAction;
import com.dolphpire.api.action.signup.credentials.AddCredentialsAction;

public class SignUpAction {

    public SignUpAction() {

    }

    public AddCredentialsAction addCredentials() {
        return new AddCredentialsAction();
    }

    public CreateAccountAction createAccount() {
        return new CreateAccountAction();
    }

}
