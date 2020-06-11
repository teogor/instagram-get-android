package com.dolphpire.api.action.igaccount.posts;

public class PostsIGAccountAction
{

    private PostsIGAccount mPostsIGAccount;

    public PostsIGAccountAction() {
        this.mPostsIGAccount = new PostsIGAccount();
    }

    public PostsIGAccountAction withUserID(String userID) {
        this.mPostsIGAccount.setUserID(userID.equals("") ? "null" : userID);
        return this;
    }

    public PostsIGAccount set() {
        return mPostsIGAccount;
    }

}
