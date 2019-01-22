package com.trainingapp.trainingapp_web.models;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;

public class ViewModelPost {
    private long id;

    @NotBlank(message = "Title cannot be empty.")
    @Length(min = 5, max = 100, message="Title must be between 5-100 characters.")
    private String title;

    @NotBlank(message = "Subtitle cannot be empty.")
    @Length(min = 5, max = 200, message="Subtitle must be between 5-200 characters.")
    private String subtitle;

    private String leadImage;

    @NotBlank(message = "Post body cannot be empty.")
    @Length(min = 5, max = 50000, message="Description must be between 5-50000 characters.")
    private String body;
    private LocalDateTime date;

    private ViewModelUser user;
    private List<ViewModelPostVote> postVotes;

    @Override
    public String toString() {
        return "ViewModelPost{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", leadImage='" + leadImage + '\'' +
                ", body='" + body + '\'' +
                ", date=" + date +
                ", user=" + user +
                ", postVotes=" + postVotes +
                '}';
    }

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
