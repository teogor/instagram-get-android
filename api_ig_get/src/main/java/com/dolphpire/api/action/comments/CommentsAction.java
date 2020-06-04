package com.dolphpire.api.action.comments;

import com.dolphpire.api.action.comments.create.CreateComment;

public class CommentsAction {

    public CommentsAction() {

    }

    public PostCommentsAction forPost(int post_id) {
        return new PostCommentsAction(post_id);
    }

    public CreateComment create() {
        return new CreateComment();
    }

}
