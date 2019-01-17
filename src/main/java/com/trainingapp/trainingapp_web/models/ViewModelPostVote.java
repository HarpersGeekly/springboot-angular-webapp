package com.trainingapp.trainingapp_web.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

public class ViewModelPostVote {

    private ViewModelPostVoteId id;

    private int type;

    private ViewModelPost post;

    private ViewModelUser user;

    public ViewModelPostVote() {}

    public ViewModelPostVote(ViewModelPost post, ViewModelUser user, int type) {
        this.id = new ViewModelPostVoteId(post.getId(), user.getId());
        this.post = post;
        this.user = user;
        this.type = type;
    }

    public static ViewModelPostVote up(ViewModelPost post, ViewModelUser user) {
        return new ViewModelPostVote(post, user, 1);
    }

    public static ViewModelPostVote down(ViewModelPost post,ViewModelUser user) {
        return new ViewModelPostVote(post, user, -1);
    }

    public boolean isUpvote() {
        return type == 1;
    }

    public boolean isDownVote() {
        return type == -1;
    }

    public boolean voteBelongsTo(ViewModelUser user) {
        return this.user.getId().equals(user.getId());
    }

    @JsonIgnore
    public ViewModelPostVoteId getId() {
        return id;
    }

    public void setId(ViewModelPostVoteId id) {
        this.id = id;
    }

    @JsonIgnore
    public ViewModelPost getPost() {
        return post;
    }

    public void setPost(ViewModelPost post) {
        this.post = post;
    }

    @JsonIgnore
    public ViewModelUser getUser() {
        return user;
    }

    public void setUser(ViewModelUser user) {
        this.user = user;
    }

    @JsonIgnore
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
