package com.trainingapp.trainingapp_web.services;

import com.trainingapp.trainingapp_web.models.ViewModelUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserManager {

    private static String userApiUrl = "http://localhost:8888/api/user/";
    private final RestTemplate restTemplate;

    @Autowired
    public UserManager(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ViewModelUser findById(Long id) {
            return this.restTemplate.getForObject(userApiUrl + "id/" + id, ViewModelUser.class);
    }

    public ViewModelUser findByUsername(String username) {
        return this.restTemplate.getForObject(userApiUrl + username, ViewModelUser.class);
    }

    public void save(ViewModelUser user) {
        System.out.println("Testing create User API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForLocation(userApiUrl, user, ViewModelUser.class);
    }
}