package com.dolphpire.api.action.igaccount.posts;

public class PostsIGAccountAction
{

    private PostsIGAccount mPostsIGAccount;

    public PostsIGAccountAction() {
        this.mPostsIGAccount = new PostsIGAccount();
    }

    public PostsIGAccountAction withUserID(String username) {
        this.mPostsIGAccount.setUserID(username.equals("") ? "null" : username);
        return this;
    }

    public PostsIGAccount set() {
        return mPostsIGAccount;
    }

}
