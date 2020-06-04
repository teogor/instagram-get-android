package com.dolphpire.api.action.posts.update;

public class UpdatePost {

    private UpdatePostAction mUpdatePostAction;

    public UpdatePost() {
        this.mUpdatePostAction = new UpdatePostAction();
    }

    public UpdatePost setPostId(int post_id) {
        this.mUpdatePostAction.setPostId(post_id);
        return this;
    }

    public UpdatePost setPostType(int post_type) {
        this.mUpdatePostAction.setPostType(post_type);
        return this;
    }

    public UpdatePost setDescription(String description) {
        this.mUpdatePostAction.setDescription(description);
        return this;
    }

    public UpdatePost setColors(String color1, String color2) {
        this.mUpdatePostAction.setColors(color1, color2);
        return this;
    }

    public UpdatePost setImages(String images_url) {
        this.mUpdatePostAction.setImages(images_url);
        return this;
    }

    public UpdatePostAction set() {
        return mUpdatePostAction;
    }

}
