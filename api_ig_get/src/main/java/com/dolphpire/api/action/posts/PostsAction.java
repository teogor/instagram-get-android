package com.dolphpire.api.action.posts;

import com.dolphpire.api.action.posts.create.CreatePost;
import com.dolphpire.api.action.posts.discover.DiscoverPostsAction;
import com.dolphpire.api.action.posts.interact.PostInteractAction;
import com.dolphpire.api.action.posts.interactions.PostInteractionsAction;
import com.dolphpire.api.action.posts.timeline.TimelinePostsAction;
import com.dolphpire.api.action.posts.update.UpdatePost;
import com.dolphpire.api.action.posts.user.UserPostsAction;

public class PostsAction {

    public PostsAction() {

    }

    public PostInteractionsAction interactions() {
        return new PostInteractionsAction();
    }

    public PostInteractAction interact() {
        return new PostInteractAction();
    }

    public TimelinePostsAction timeline() {
        return new TimelinePostsAction();
    }

    public DiscoverPostsAction discover() {
        return new DiscoverPostsAction();
    }

    public UserPostsAction user() {
        return new UserPostsAction();
    }

    public UpdatePost update() {
        return new UpdatePost();
    }

    public CreatePost create() {
        return new CreatePost();
    }

}
