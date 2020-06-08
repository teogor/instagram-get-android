package com.dolphpire.api.action.igaccount.addAccount;

public class LinkIGAccountAction
{

    private LinkIGAccount mLinkIGAccount;

    public LinkIGAccountAction() {
        this.mLinkIGAccount = new LinkIGAccount();
    }

    public LinkIGAccountAction withIGID(long name) {
        this.mLinkIGAccount.setIGID(String.valueOf(name));
        return this;
    }

    public LinkIGAccountAction withPassword(String password) {
        this.mLinkIGAccount.setPassword(password.equals("") ? "null" : password);
        return this;
    }

    public LinkIGAccountAction withUsername(String username) {
        this.mLinkIGAccount.setUsername(username.equals("") ? "null" : username);
        return this;
    }

    public LinkIGAccountAction withProfilePicture(String profile_picture) {
        this.mLinkIGAccount.setProfilePicture(profile_picture.equals("") ? "null" : profile_picture);
        return this;
    }

    public LinkIGAccountAction isPrivate(boolean is_private) {
        this.mLinkIGAccount.setIsPrivate(String.valueOf(is_private));
        return this;
    }

    public LinkIGAccount set() {
        return mLinkIGAccount;
    }

}
