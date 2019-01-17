package com.trainingapp.trainingapp_web.controllers;

import com.trainingapp.trainingapp_web.models.ViewModelUser;
import com.trainingapp.trainingapp_web.services.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UsersController {

    private UserManager userMgr;

    @Autowired
    public UsersController(UserManager userMgr) {
        this.userMgr = userMgr;
    }

    @GetMapping("/profile")
    public String showProfile(HttpServletRequest request) {
        ViewModelUser sessionUser = (ViewModelUser) request.getSession().getAttribute("user");
        if (sessionUser == null) {
            return "redirect:/login";
        }
        ViewModelUser user = userMgr.findById(sessionUser.getId());
        return "redirect:/profile/" + user.getId() + '/' + user.getUsername();
    }

    @GetMapping("/profile/{id}/{username}")
    public String showOtherUsersProfile(@PathVariable Long id, Model viewModel) {
        System.out.println("get here profile");
        System.out.println("id: "  + id);
        System.out.println(userMgr.findById(id));
        ViewModelUser user = userMgr.findById(id);
        viewModel.addAttribute("user", user);
        System.out.println(user.getId());
        return "users/profile";
    }




}
