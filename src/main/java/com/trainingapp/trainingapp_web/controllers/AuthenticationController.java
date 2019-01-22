package com.trainingapp.trainingapp_web.controllers;

import com.trainingapp.trainingapp_web.models.ViewModelPassword;
import com.trainingapp.trainingapp_web.models.ViewModelUser;
import com.trainingapp.trainingapp_web.services.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
public class AuthenticationController {

    private UserManager userMgr;

    @Autowired
    public AuthenticationController(UserManager userMgr) {
        this.userMgr = userMgr;
    }

    //------------------------------------------- Login -------------------------------------------------

    @GetMapping("/login")
    public String showLoginForm(Model viewModel, HttpServletRequest request) {
        ViewModelUser user = (ViewModelUser) request.getSession().getAttribute("user");
        if (user != null) {
            return "redirect:/profile";
        }
        viewModel.addAttribute("message", "Welcome to the login page - TEST");
        return "users/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") ViewModelUser user, Model viewModel, HttpServletRequest request, RedirectAttributes redirect) {
        boolean usernameIsEmpty = user.getUsername().isEmpty();
        boolean passwordIsEmpty = user.getPassword().isEmpty();
        ViewModelUser existingUser = userMgr.findByUsername(user.getUsername());
        boolean userExists = (existingUser != null);
        System.out.println(existingUser);
        System.out.println(existingUser.getPassword());
//        boolean validAttempt = userExists && existingUser.getUsername().equals(user.getUsername()) && Password.check(user.getPassword(), existingUser.getPassword());

//        if (userExists && !passwordIsEmpty) {
////            boolean passwordCorrect =  Password.check(user.getPassword(), existingUser.getPassword()); // check the submitted password against what I have in the database
//            // incorrect password:
//            if (!passwordCorrect) {
//                viewModel.addAttribute("passwordIsIncorrect", true);
//                viewModel.addAttribute("user", user);
//                return "users/login";
//            }
//        }

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

//        if (!validAttempt) {
//            System.out.println("not a valid attempt");
//            viewModel.addAttribute("errorMessage", true);
//            viewModel.addAttribute("user", user);
//            return "users/login";
//        } else {
            request.getSession().setAttribute("user", existingUser);
            redirect.addFlashAttribute("user", existingUser);
            return "redirect:/profile";
//        }
    }

//-------------------------------------- Logout ----------------------------------------------------

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        request.getSession().invalidate();
        return "redirect:/login";
    }

//-------------------------------------- Register --------------------------------------------------

    @GetMapping("/register")
    public String register(Model viewModel, HttpServletRequest request) {
        ViewModelUser user = (ViewModelUser) request.getSession().getAttribute("user");
        if (user != null) {
            return "redirect:/profile";
        }
        viewModel.addAttribute("user", new ViewModelUser());
        return "users/register";
    }

    @PostMapping("/register")
    public String register(
            @Valid @ModelAttribute("user") ViewModelUser user,
            BindingResult result,
            Model viewModel,
            HttpServletRequest request,
            @RequestParam(name = "confirm_password") String passwordConfirmation) {

        if (user.getBio().isEmpty()) {
            user.setBio(null);
        } else {
            user.setBio(user.getBio());
        }

        ViewModelUser existingUser = userMgr.findByUsername(user.getUsername());
        if (existingUser != null) {
            result.rejectValue(
                    "username",
                    "user.username",
                    "Username is already taken.");
        }

        ViewModelUser existingEmail = userMgr.findByEmail(user.getEmail());
        if (existingEmail != null) {
            result.rejectValue(
                    "email",
                    "user.email",
                    "Email is already used.");
        }

        //compare passwords:
        if (!passwordConfirmation.equals(user.getPassword())) {
            result.rejectValue(
                    "password",
                    "user.password",
                    "Your passwords do not match.");
        }

        // if there are errors, show the form again.
        if (result.hasErrors()) {
            result.getFieldErrors().stream().forEach(f -> System.out.println((f.getField() + ": " + f.getDefaultMessage())));
            System.out.println("errors");
            viewModel.addAttribute("errors", result);
            viewModel.addAttribute("user", user);
            return "users/register";
        }

//        user.setPassword(ViewModelPassword.hash(user.getPassword()));
        user.setPassword(user.getPassword());
        user.setDate(LocalDateTime.now());
        System.out.println("user:" + user.toString());
        userMgr.save(user);
        request.getSession().setAttribute("user", user);
        return "redirect:/profile";
    }





}
