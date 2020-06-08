package com.dolphpire.api.action.user.details;

public class DetailsAction {

    public DetailsAction() {

    }

    public UserFullDetailsAction withUUID(int user_id) {
        return new UserFullDetailsAction(user_id);
    }

    public UserFullDetailsAction withUsername(String username) {
        return new UserFullDetailsAction(username);
    }

}
