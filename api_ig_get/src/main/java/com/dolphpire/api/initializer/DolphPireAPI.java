package com.dolphpire.api.initializer;

import com.dolphpire.api.action.autocomplete.AutocompleteAction;
import com.dolphpire.api.action.comments.CommentsAction;
import com.dolphpire.api.action.discover.DiscoverAction;
import com.dolphpire.api.action.hashtag.HashtagAction;
import com.dolphpire.api.action.igaccount.IGAccountAction;
import com.dolphpire.api.action.login.LoginAction;
import com.dolphpire.api.action.notifications.Notifications;
import com.dolphpire.api.action.posts.PostsAction;
import com.dolphpire.api.action.signup.SignUpAction;
import com.dolphpire.api.action.user.UserAction;

public class DolphPireAPI
{

    @KeepForApi
    public LoginAction login()
    {
        return new LoginAction();
    }

    @KeepForApi
    public SignUpAction signup()
    {
        return new SignUpAction();
    }

    @KeepForApi
    public PostsAction posts()
    {
        return new PostsAction();
    }

    @KeepForApi
    public UserAction user()
    {
        return new UserAction();
    }

    @KeepForApi
    public IGAccountAction igAccount()
    {
        return new IGAccountAction();
    }

    @KeepForApi
    public AutocompleteAction autocomplete()
    {
        return new AutocompleteAction();
    }

    @KeepForApi
    public HashtagAction hashtag()
    {
        return new HashtagAction();
    }

    @KeepForApi
    public CommentsAction comments()
    {
        return new CommentsAction();
    }

    @KeepForApi
    public Notifications notifications()
    {
        return new Notifications();
    }

    @KeepForApi
    public DiscoverAction discover()
    {
        return new DiscoverAction();
    }

}
