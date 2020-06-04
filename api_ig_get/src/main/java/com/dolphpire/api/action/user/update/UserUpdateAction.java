package com.dolphpire.api.action.user.update;

import com.dolphpire.api.action.user.update.cover.UpdateCoverAction;
import com.dolphpire.api.action.user.update.credentials.UpdateCredentialsAction;
import com.dolphpire.api.action.user.update.details.UpdateDetailsAction;
import com.dolphpire.api.action.user.update.featured.UpdateFeaturedProfilesAction;
import com.dolphpire.api.action.user.update.privateAccount.UpdatePrivateAccount;
import com.dolphpire.api.action.user.update.profilePicture.UpdateProfilePictureAction;
import com.dolphpire.api.action.user.update.visibility.UpdateVisibilityAction;

public class UserUpdateAction {

    public UserUpdateAction() {

    }

    public UpdateCredentialsAction credentials() {
        return new UpdateCredentialsAction();
    }

    public UpdateDetailsAction details() {
        return new UpdateDetailsAction();
    }

    public UpdateVisibilityAction visibility() {
        return new UpdateVisibilityAction();
    }

    public UpdateCoverAction cover() {
        return new UpdateCoverAction();
    }

    public UpdateProfilePictureAction profilePicture() {
        return new UpdateProfilePictureAction();
    }

    public UpdateFeaturedProfilesAction featuredProfiles() {
        return new UpdateFeaturedProfilesAction();
    }

    public UpdatePrivateAccount privateAccount(boolean makePrivate) {
        return new UpdatePrivateAccount(makePrivate);
    }

}
