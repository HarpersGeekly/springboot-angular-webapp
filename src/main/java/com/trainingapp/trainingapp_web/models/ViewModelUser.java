package com.trainingapp.trainingapp_web.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ViewModelUser {

    private Long id;

    @Pattern(regexp = "(?=^.{3,20}$)^[a-zA-Z][a-zA-Z0-9 ]*[._-]?[a-zA-Z0-9 ]+$", message = "Username must be alphanumeric only.")
    @NotBlank(message="Please enter a username.")
    @Length(min = 2, max = 20, message="Your username must be between 2-20 characters.")
    private String username;

    @NotBlank(message = "Please enter an email address.")
    @Email(message = "That email is not a valid email address.")
    private String email;

    @Length(max = 1500, message="Your bio must be less than 1500 characters.")
    private String bio;

    @NotBlank(message = "Your password cannot be empty.")
    @Length(min = 8, max = 100, message="Your password must be between 8-100 characters.") // BCrypt PasswordEncoder hashes passwords with 60 random characters. Make sure the max is >= 60
    @JsonIgnore //password is hidden from the client
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
