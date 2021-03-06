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

    public ViewModelUser save(ViewModelUser user) {
        System.out.println("get to UserManager save()");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<ViewModelUser> entity = new HttpEntity<>(user, headers);
        ResponseEntity<ViewModelUser> response = restTemplate.exchange( userApiUrl + "saveUser", HttpMethod.POST, entity, ViewModelUser.class);
        return response.getBody();
    }

    public void edit(ViewModelUser user) {
        postToApi(user, "editUser", HttpMethod.PUT);
    }

    public void delete(ViewModelUser user) {
        postToApi(user, "deleteUser", HttpMethod.DELETE);
    }

    private void postToApi(ViewModelUser user, String uniqueUrlPath, HttpMethod httpMethod) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<ViewModelUser> entity = new HttpEntity<>(user, headers);
        ResponseEntity<ViewModelUser> response = restTemplate.exchange( userApiUrl + uniqueUrlPath, httpMethod, entity, ViewModelUser.class);
    }
}
