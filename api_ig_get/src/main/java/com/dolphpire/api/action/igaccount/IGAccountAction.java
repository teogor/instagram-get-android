package com.dolphpire.api.action.igaccount;

import com.dolphpire.api.action.igaccount.addAccount.LinkIGAccountAction;
import com.dolphpire.api.action.igaccount.followers.FollowersIGAccountAction;
import com.dolphpire.api.action.igaccount.posts.PostsIGAccount;
import com.dolphpire.api.action.igaccount.posts.PostsIGAccountAction;

public class IGAccountAction
{

    public IGAccountAction()
    {

    }

    public LinkIGAccountAction linkAccount()
    {
        return new LinkIGAccountAction();
    }

    public FollowersIGAccountAction followersCount()
    {
        return new FollowersIGAccountAction();
    }

    public PostsIGAccountAction posts()
    {
        return new PostsIGAccountAction();
    }

}
