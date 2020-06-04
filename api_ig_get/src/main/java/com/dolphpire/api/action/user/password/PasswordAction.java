package com.dolphpire.api.action.user.password;

public class PasswordAction {

    private ChangePassword mChangePassword;

    public PasswordAction() {
        this.mChangePassword = new ChangePassword();
    }

    public PasswordAction oldPassword(String password) {
        this.mChangePassword.setOldPassword(password);
        return this;
    }

    public PasswordAction newPassword(String password) {
        this.mChangePassword.setNewPassword(password);
        return this;
    }

    public ChangePassword set() {
        return mChangePassword;
    }

}
