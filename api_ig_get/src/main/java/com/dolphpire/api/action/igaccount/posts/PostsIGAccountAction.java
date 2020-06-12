package com.dolphpire.api.action.igaccount.posts;

public class PostsIGAccountAction
{

    private PostsIGAccount mPostsIGAccount;

    public PostsIGAccountAction() {
        this.mPostsIGAccount = new PostsIGAccount();
    }

    public PostsIGAccountAction withUserID(int userID) {
        this.mPostsIGAccount.setUserID(String.valueOf(userID));
        return this;
    }

    public PostsIGAccount set() {
        return mPostsIGAccount;
    }

}
