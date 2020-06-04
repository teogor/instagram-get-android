package com.dolphpire.api.action.user.details;

public class DetailsAction {

    public DetailsAction() {

    }

    public UserFullDetailsAction advanced(int user_id) {
        return new UserFullDetailsAction(user_id);
    }

    public UserFullDetailsAction advanced(String username) {
        return new UserFullDetailsAction(username);
    }

    public UserProfilePicturesAction profilePictures(int user_id) {
        return new UserProfilePicturesAction(user_id);
    }

}
