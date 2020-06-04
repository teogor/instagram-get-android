package com.dolphpire.api.action.posts.create;

public class CreatePost {

    private CreatePostAction mCreatePostAction;

    public CreatePost() {
        this.mCreatePostAction = new CreatePostAction();
    }

    public CreatePost setPostType(int post_type) {
        this.mCreatePostAction.setPostType(post_type);
        return this;
    }

    public CreatePost setDescription(String description) {
        this.mCreatePostAction.setDescription(description);
        return this;
    }

    public CreatePost setColors(String color1, String color2) {
        this.mCreatePostAction.setColors(color1, color2);
        return this;
    }

    public CreatePost setImages(String images_url) {
        this.mCreatePostAction.setImages(images_url);
        return this;
    }

    public CreatePostAction set() {
        return mCreatePostAction;
    }

}
