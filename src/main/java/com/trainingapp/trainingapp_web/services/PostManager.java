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

    public ViewModelPost findById(Long id) {
        return this.restTemplate.getForObject(postApiUrl + "id/" + id, ViewModelPost.class);
    }

    public void save(ViewModelPost post) {
        System.out.println("Testing create User API----------");
        this.restTemplate.postForEntity(postApiUrl + "save", post, ViewModelPost.class);

//        HttpHeaders requestHeaders = new HttpHeaders();
//        requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
//        HttpEntity<ViewModelPost> requestEntity = new HttpEntity<>(post,requestHeaders);
//        ResponseEntity<ViewModelPost> postResponse = restTemplate.exchange(postApiUrl + "save", HttpMethod.POST, requestEntity, ViewModelPost.class);
    }


}
