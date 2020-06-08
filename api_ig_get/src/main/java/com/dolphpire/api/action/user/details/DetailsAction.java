package com.dolphpire.api.action.user.details;

public class DetailsAction {

    public DetailsAction() {

    }

    public UserDetailsAction withUUID(int user_id) {
        return new UserDetailsAction(user_id);
    }

    public UserDetailsAction withUsername(String username) {
        return new UserDetailsAction(username);
    }

}
