package com.trainingapp.trainingapp_web.services;

import com.trainingapp.trainingapp_web.models.ViewModelPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PostManager {

    private static String postApiUrl = "http://localhost:8888/api/post/";
    private final RestTemplate restTemplate;

    @Autowired
    public PostManager(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ViewModelPost[] fetchPosts() {
        return this.restTemplate.getForObject(postApiUrl + "posts", ViewModelPost[].class);
    }

    public ViewModelPost[] findAllPostsByUserId(Long id) {
        return this.restTemplate.getForObject(postApiUrl + "postsByUserId/" + id, ViewModelPost[].class);
    }

    public ViewModelPost findById(Long id) {
        return this.restTemplate.getForObject(postApiUrl + "postById/" + id, ViewModelPost.class);
    }

    public ViewModelPost save(ViewModelPost post) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<ViewModelPost> entity = new HttpEntity<>(post, headers);
        ResponseEntity<ViewModelPost> response = restTemplate.exchange( postApiUrl + "savePost", HttpMethod.POST, entity, ViewModelPost.class);
        return response.getBody();
    }

    public void edit(ViewModelPost post) {
        postToApi(post, "editPost", HttpMethod.PUT);
    }

    public void delete(ViewModelPost post) {
        postToApi(post, "deletePost", HttpMethod.DELETE);
    }

    private void postToApi(ViewModelPost user, String uniqueUrlPath, HttpMethod httpMethod) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<ViewModelPost> entity = new HttpEntity<>(user, headers);
        ResponseEntity<ViewModelPost> response = restTemplate.exchange( postApiUrl + uniqueUrlPath, httpMethod, entity, ViewModelPost.class);
    }
}
