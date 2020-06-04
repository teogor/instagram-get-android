package com.dolphpire.api.action.user.check;

public class DataCheckAction {

    public DataCheckAction() {

    }

    public CheckCredentialsAction username(String username) {
        return new CheckCredentialsAction(username);
    }

    public CheckCredentialsAction email(String email) {
        return new CheckCredentialsAction(email);
    }

    public CheckCredentialsAction phone(String phone) {
        return new CheckCredentialsAction(phone);
    }

}
