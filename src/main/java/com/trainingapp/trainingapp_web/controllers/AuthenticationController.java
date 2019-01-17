package com.trainingapp.trainingapp_web.controllers;

import com.trainingapp.trainingapp_web.models.ViewModelPassword;
import com.trainingapp.trainingapp_web.models.ViewModelUser;
import com.trainingapp.trainingapp_web.services.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthenticationController {

    private UserManager userMgr;

    @Autowired
    public AuthenticationController(UserManager userMgr) {
        this.userMgr = userMgr;
    }

    @GetMapping("/login")
    public String showLoginForm(Model viewModel, HttpServletRequest request) {
        ViewModelUser user = (ViewModelUser) request.getSession().getAttribute("user");
        if (user != null) {
            return "redirect:/profile";
        }
        viewModel.addAttribute("user", new ViewModelUser());
        return "users/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") ViewModelUser user, Model viewModel, HttpServletRequest request, RedirectAttributes redirect) {
        boolean usernameIsEmpty = user.getUsername().isEmpty();
        boolean passwordIsEmpty = user.getPassword().isEmpty();
        System.out.println("username:" + user.getUsername());
        System.out.println("password:" + user.getPassword());
        ViewModelUser existingUser = userMgr.findByUsername(user.getUsername());
        System.out.println("db username: " + existingUser.getUsername());
        System.out.println("db password: " + existingUser.getPassword());
        boolean userExists = (existingUser != null);

        boolean validAttempt = userExists && existingUser.getUsername().equals(user.getUsername()) && ViewModelPassword.check(user.getPassword(), existingUser.getPassword());

        if (userExists && !passwordIsEmpty) {
            boolean passwordCorrect =  ViewModelPassword.check(user.getPassword(), existingUser.getPassword()); // check the submitted password against what I have in the database
            // incorrect password:
            if (!passwordCorrect) {
                viewModel.addAttribute("passwordIsIncorrect", true);
                viewModel.addAttribute("user", user);
                return "users/login";
            }
        }

        // both empty...
        if (usernameIsEmpty && passwordIsEmpty) {
            viewModel.addAttribute("usernameIsEmpty", true);
            viewModel.addAttribute("passwordIsEmpty", true);
            return "users/login";
        }
        //one or the other...
        if (!usernameIsEmpty && passwordIsEmpty) {
            viewModel.addAttribute("passwordIsEmpty", true);
            viewModel.addAttribute("user", user);
            return "users/login";
        }
        if (usernameIsEmpty && !passwordIsEmpty) {
            viewModel.addAttribute("usernameIsEmpty", true);
            viewModel.addAttribute("user", user);
            return "users/login";
        }

        // username doesn't exist:
        if ((!usernameIsEmpty && !passwordIsEmpty) && !userExists) {
            request.setAttribute("userNotExist", !userExists);
            return "users/login";
        }

        if (!validAttempt) {
            System.out.println("not a valid attempt");
            viewModel.addAttribute("errorMessage", true);
            viewModel.addAttribute("user", user);
            return "users/login";
        } else {
            request.getSession().setAttribute("user", existingUser);
            redirect.addFlashAttribute("user", existingUser);
            return "redirect:/profile";
        }
    }


    @GetMapping("/register")
    public String register(Model viewModel, HttpServletRequest request) {
//        User user = (User) request.getSession().getAttribute("user");
//        if (user != null) {
//            return "redirect:/profile";
//        }
        viewModel.addAttribute("user", new ViewModelUser());
        return "users/register";
    }






}
