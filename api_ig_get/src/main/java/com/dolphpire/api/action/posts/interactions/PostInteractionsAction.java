package com.dolphpire.api.action.posts.interactions;

public class PostInteractionsAction {

    public PostInteractionsAction() {

    }

    public PostInteractionsViewAction viewLikes(int post_id) {
        return new PostInteractionsViewAction(post_id, 0);
    }

    public PostInteractionsViewAction viewUnlikes(int post_id) {
        return new PostInteractionsViewAction(post_id, 1);
    }

}
