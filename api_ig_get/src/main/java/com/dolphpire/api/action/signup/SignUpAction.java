package com.dolphpire.api.action.signup;

import com.dolphpire.api.action.signup.account.CreateAccountAction;

public class SignUpAction
{

    public SignUpAction()
    {

    }

    public CreateAccountAction createAccount()
    {
        return new CreateAccountAction();
    }

}
