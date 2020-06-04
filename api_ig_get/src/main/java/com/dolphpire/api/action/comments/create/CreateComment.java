package com.dolphpire.api.action.comments.create;

public class CreateComment {

    private CreateCommentAction mCreateCommentAction;

    public CreateComment() {
        this.mCreateCommentAction = new CreateCommentAction();
    }

    public CreateComment setPostId(int post_id) {
        this.mCreateCommentAction.setPostId(post_id);
        return this;
    }

    public CreateComment setComment(String comment) {
        this.mCreateCommentAction.setComment(comment);
        return this;
    }

    public CreateCommentAction set() {
        return mCreateCommentAction;
    }

}
