package com.dolphpire.api.action.user.interact;

public class UserInteractAction {

    public UserInteractAction() {

    }

    public UserInteract follow(int user_id) {
        return new UserInteract(user_id, 1, 1);
    }

    public UserInteract unfollow(int user_id) {
        return new UserInteract(user_id, 0, 1);
    }

    public UserInteract sendRequest(int user_id) {
        return new UserInteract(user_id, 1, 0);
    }

    public UserInteract cancelRequest(int user_id) {
        return new UserInteract(user_id, 0, 0);
    }

}
