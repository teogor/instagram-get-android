package com.dolphpire.api.action.igaccount.posts;

public class PostsIGAccountAction
{

    private PostsIGAccount mPostsIGAccount;

    public PostsIGAccountAction() {
        this.mPostsIGAccount = new PostsIGAccount();
    }

    public PostsIGAccountAction withUserID(long igUserID) {
        this.mPostsIGAccount.setUserID(String.valueOf(igUserID));
        return this;
    }

    public PostsIGAccount set() {
        return mPostsIGAccount;
    }

}
