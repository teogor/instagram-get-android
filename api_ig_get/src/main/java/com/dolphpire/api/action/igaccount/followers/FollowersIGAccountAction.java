package com.dolphpire.api.action.igaccount.followers;

public class FollowersIGAccountAction
{

    private FollowersIGAccount mFollowersIGAccount;

    public FollowersIGAccountAction() {
        this.mFollowersIGAccount = new FollowersIGAccount();
    }

    public FollowersIGAccountAction withUsername(String username) {
        this.mFollowersIGAccount.setUsername(username.equals("") ? "null" : username);
        return this;
    }

    public FollowersIGAccount set() {
        return mFollowersIGAccount;
    }

}
