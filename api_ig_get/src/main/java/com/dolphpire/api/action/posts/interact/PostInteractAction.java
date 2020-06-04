package com.dolphpire.api.action.posts.interact;

public class PostInteractAction {

    public PostInteractAction() {

    }

    public UpdatePostInteractionAction like(int post_id) {
        return new UpdatePostInteractionAction(post_id, 0);
    }

    public UpdatePostInteractionAction unlike(int post_id) {
        return new UpdatePostInteractionAction(post_id, 1);
    }

    public UpdatePostInteractionAction save(int post_id) {
        return new UpdatePostInteractionAction(post_id, 2);
    }

    public UpdatePostInteractionAction deleteLike(int post_id) {
        return new UpdatePostInteractionAction(post_id, 3);
    }

    public UpdatePostInteractionAction deleteUnlike(int post_id) {
        return new UpdatePostInteractionAction(post_id, 4);
    }

    public UpdatePostInteractionAction deleteSaved(int post_id) {
        return new UpdatePostInteractionAction(post_id, 5);
    }

    public UpdatePostInteractionAction deletePost(int post_id) {
        return new UpdatePostInteractionAction(post_id, 6);
    }

}
