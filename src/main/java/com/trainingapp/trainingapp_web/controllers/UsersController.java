package com.trainingapp.trainingapp_web.controllers;

import com.trainingapp.trainingapp_web.models.ViewModelUser;
import com.trainingapp.trainingapp_web.services.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        ViewModelUser user = userMgr.findById(id);
        viewModel.addAttribute("user", user);
        return "users/profile";
    }

    @PostMapping("/editUser")
    public void editUser(ViewModelUser user) {
        userMgr.edit(user);
    }

    @PostMapping("/deleteUser")
    @ResponseStatus(HttpStatus.OK) //used to redirect with flash attributes.
    public ModelAndView deleteUser(@RequestBody ViewModelUser user, HttpServletRequest request, RedirectAttributes redirect) {
        userMgr.delete(user);
        boolean deleted = userMgr.findById(user.getId()) == null;
        if(deleted) {
            request.getSession().removeAttribute("user");
            request.getSession().invalidate();
            redirect.addFlashAttribute("deleteIsSuccessful", true);
            redirect.addFlashAttribute("successMessage", "We're sorry to see you go! Your account has been removed from the database.");
            return new ModelAndView("redirect:/register");
        }
        return new ModelAndView("users/profile");
    }




}
