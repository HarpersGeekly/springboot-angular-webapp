package com.trainingapp.trainingapp_web.models;

import java.io.Serializable;

public class ViewModelPostVoteId implements Serializable{

    private long postId;
    private long userId;

    public ViewModelPostVoteId() {}

    public ViewModelPostVoteId(Long postId, Long userId) {
        this.postId = postId;
        this.userId = userId;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
