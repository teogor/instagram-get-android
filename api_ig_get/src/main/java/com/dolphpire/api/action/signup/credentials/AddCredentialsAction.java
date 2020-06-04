package com.dolphpire.api.action.signup.credentials;

public class AddCredentialsAction {

    private CredentialsSignUpAction mCredentialsSignUpAction;

    public AddCredentialsAction() {
        this.mCredentialsSignUpAction = new CredentialsSignUpAction();
    }

    public AddCredentialsAction setUsername(String username) {
        this.mCredentialsSignUpAction.setUsername(username);
        return this;
    }

    public AddCredentialsAction setEmail(String email) {
        this.mCredentialsSignUpAction.setEmail(email);
        return this;
    }

    public CredentialsSignUpAction set() {
        return mCredentialsSignUpAction;
    }

}
