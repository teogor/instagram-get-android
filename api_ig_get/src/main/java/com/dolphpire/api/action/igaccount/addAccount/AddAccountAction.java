package com.dolphpire.api.action.igaccount.addAccount;

public class AddAccountAction
{

    private AddAccount mAddAccount;

    public AddAccountAction() {
        this.mAddAccount = new AddAccount();
    }

    public AddAccountAction withIGID(long name) {
        this.mAddAccount.setIGID(String.valueOf(name));
        return this;
    }

    public AddAccountAction withPassword(String password) {
        this.mAddAccount.setPassword(password.equals("") ? "null" : password);
        return this;
    }

    public AddAccountAction withUsername(String username) {
        this.mAddAccount.setUsername(username.equals("") ? "null" : username);
        return this;
    }

    public AddAccountAction withProfilePicture(String profile_picture) {
        this.mAddAccount.setProfilePicture(profile_picture.equals("") ? "null" : profile_picture);
        return this;
    }

    public AddAccount set() {
        return mAddAccount;
    }

}
