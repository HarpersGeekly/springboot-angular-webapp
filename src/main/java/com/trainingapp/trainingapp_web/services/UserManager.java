package com.trainingapp.trainingapp_web.services;

import com.trainingapp.trainingapp_web.models.ViewModelUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
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
        return this.restTemplate.getForObject(userApiUrl + "username/" + username, ViewModelUser.class);
    }

    public ViewModelUser findByEmail(String email) {
        return this.restTemplate.getForObject(userApiUrl + "email/" + email, ViewModelUser.class);
    }

    public void save(ViewModelUser user) {
////        RestTemplate restTemplate = new RestTemplate();
////        restTemplate.postForObject(userApiUrl, user, ViewModelUser.class);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<ViewModelUser> entity = new HttpEntity<>(user, headers);
        ResponseEntity<ViewModelUser> response = restTemplate.exchange( userApiUrl + "saveUser", HttpMethod.POST, entity, ViewModelUser.class);
    }

    public void delete(ViewModelUser user) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<ViewModelUser> entity = new HttpEntity<>(user, headers);
        ResponseEntity<ViewModelUser> response = restTemplate.exchange( userApiUrl + "deleteUser", HttpMethod.POST, entity, ViewModelUser.class);
    }
}
