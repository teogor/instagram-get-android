package com.dolphpire.api.action.user.authenticate;

public class UserAuthCheck {

    public UserAuthCheck() {

    }

    public UserAuthLogKey logKey(String logKey) {
        return new UserAuthLogKey(logKey);
    }

}
