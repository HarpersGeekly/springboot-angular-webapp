package com.trainingapp.trainingapp_web.models;

import java.time.LocalDateTime;
import java.util.List;

public class ViewModelPost {

    private long id;
    private String title;
    private String subtitle;
    private String leadImage;
    private String body;
    private LocalDateTime date;

    private ViewModelUser user;
    private List<ViewModelPostVote> postVotes;

    public ViewModelPost(){}


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getLeadImage() {
        return leadImage;
    }

    public void setLeadImage(String leadImage) {
        this.leadImage = leadImage;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public ViewModelUser getUser() {
        return user;
    }

    public void setUser(ViewModelUser user) {
        this.user = user;
    }

    public List<ViewModelPostVote> getPostVotes() {
        return postVotes;
    }

    public void setPostVotes(List<ViewModelPostVote> postVotes) {
        this.postVotes = postVotes;
    }
}
