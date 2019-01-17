package com.trainingapp.trainingapp_web.models;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ViewModelUser {

    private Long id;
    private String username;
    private String email;
    private String bio;
    private String password;
    private LocalDateTime date;

    public ViewModelUser(){}

//    ============================= relationships ==========================

    private List<ViewModelPost> posts;
    private List<ViewModelPostVote> postVotes;

//    ============================ getters and setters =====================

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @JsonGetter("hoursMinutes")
    public String hoursMinutes() {
        return date.format(DateTimeFormatter.ofPattern("h:mm a"));
    }

    @JsonGetter("formatDate")
    public String formatDate() {
        return date.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"));
    }
//
    public List<ViewModelPost> getPosts() {
        return posts;
    }

    public void setPosts(List<ViewModelPost> posts) {
        this.posts = posts;
    }

    public List<ViewModelPostVote> getPostVotes() {
        return postVotes;
    }

    public void setVotes(List<ViewModelPostVote> votes) {
        this.postVotes = postVotes;
    }
}
