package com.trainingapp.trainingapp_web.services;

import com.trainingapp.trainingapp_web.models.ViewModelPost;
import org.springframework.beans.factory.annotation.Autowired;
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
        return this.restTemplate.getForObject(postApiUrl + "/posts", ViewModelPost[].class);
    }

    public ViewModelPost[] findAllPostsByUserId(Long id) {
        return this.restTemplate.getForObject(postApiUrl + "/postsByUserId/" + id, ViewModelPost[].class);
    }

    public ViewModelPost findById(Long id) {
        return this.restTemplate.getForObject(postApiUrl + "id/" + id, ViewModelPost.class);
    }

    public void save(ViewModelPost post) {
        System.out.println("Testing create User API----------");
        this.restTemplate.postForEntity(postApiUrl + "save", post, ViewModelPost.class);
    }


}
