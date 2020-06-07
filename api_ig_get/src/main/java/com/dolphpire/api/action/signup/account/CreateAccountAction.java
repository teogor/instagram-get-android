package com.dolphpire.api.action.signup.account;

public class CreateAccountAction {

    private CreateAccount mCreateAccount;

    public CreateAccountAction() {
        this.mCreateAccount = new CreateAccount();
    }

    public CreateAccountAction setUsername(String username) {
        this.mCreateAccount.setUsername(username);
        return this;
    }

    public CreateAccountAction setPassword(String password) {
        this.mCreateAccount.setPassword(password);
        return this;
    }

    public CreateAccountAction setEmail(String email) {
        this.mCreateAccount.setEmail(email);
        return this;
    }

    public CreateAccount set() {
        return mCreateAccount;
    }

}
