package com.dolphpire.api.action.login;

public class LoginAction {

    private Login mLogin;

    public LoginAction() {
        this.mLogin = new Login();
    }

    public LoginAction withLoginKey(String loginKey) {
        this.mLogin.setLoginKey(loginKey);
        return this;
    }

    public LoginAction withPassword(String password) {
        this.mLogin.setPassword(password);
        return this;
    }

    public Login set() {
        return mLogin;
    }

}
