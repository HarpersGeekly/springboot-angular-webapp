package com.trainingapp.trainingapp_web.services;

import com.trainingapp.trainingapp_web.models.ViewModelPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PostManager {

    private static String postApiUrl = "http://localhost:8888/api/post/";
    private final RestTemplate restTemplate;

    @Autowired
    public PostManager(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ViewModelPost findById(Long id) {
        return this.restTemplate.getForObject(postApiUrl + "id/" + id, ViewModelPost.class);
    }

}
