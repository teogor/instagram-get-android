package com.dolphpire.api.action.posts.user;

public class UserPostsAction {

    public UserPostsAction() {

    }

    public UserPostsGetAction ofUser(int user_id) {
        return new UserPostsGetAction(user_id, 0);
    }

    public UserPostsGetAction tagged(int user_id) {
        return new UserPostsGetAction(user_id, 1);
    }

    public UsersPostsInteractedAction interacted() {
        return new UsersPostsInteractedAction();
    }

    public UsersPostsSavedAction saved() {
        return new UsersPostsSavedAction();
    }

}
