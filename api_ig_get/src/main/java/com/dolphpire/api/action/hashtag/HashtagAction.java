package com.dolphpire.api.action.hashtag;

public class HashtagAction {

    public HashtagAction() {

    }

    public HashtagLengthAction totalPosts(String withHashtag) {
        return new HashtagLengthAction(withHashtag);
    }

    public HashtagPostsAction recentPosts(String withHashtag) {
        return new HashtagPostsAction(withHashtag, 0);
    }

    public HashtagPostsAction topPosts(String withHashtag) {
        return new HashtagPostsAction(withHashtag, 1);
    }

}
