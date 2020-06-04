package com.dolphpire.api.action.user.update.profilePicture;

public class UpdateProfilePictureAction {

    public UpdateProfilePictureAction() {

    }

    public DeleteAction delete(String image_id) {
        return new DeleteAction(image_id);
    }

    public UpdateAction update(String image_id) {
        return new UpdateAction(image_id);
    }

    public UploadAction upload(String image_id) {
        return new UploadAction(image_id);
    }

}
