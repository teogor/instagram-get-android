package com.dolphpire.api.action.igaccount;

import com.dolphpire.api.action.igaccount.addAccount.LinkIGAccountAction;
import com.dolphpire.api.action.igaccount.followers.FollowersIGAccountAction;

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

}
