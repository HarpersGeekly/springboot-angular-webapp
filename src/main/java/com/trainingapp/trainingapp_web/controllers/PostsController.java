package com.trainingapp.trainingapp_web.controllers;

import com.trainingapp.trainingapp_web.models.ViewModelPost;
import com.trainingapp.trainingapp_web.models.ViewModelUser;
import com.trainingapp.trainingapp_web.services.PostManager;
import com.trainingapp.trainingapp_web.services.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Controller
public class PostsController {

    private PostManager postMgr;
    private UserManager userMgr;

    @Autowired
    public PostsController(PostManager postMgr, UserManager userMgr) {
        this.postMgr = postMgr;
        this.userMgr = userMgr;
    }

    @GetMapping("/")
    public String index() {
        System.out.println("get to Home Page");
        return "index";
    }

    @GetMapping("/fetchPosts")
    @ResponseBody
    public List<ViewModelPost> fetchPosts() {
        return Arrays.asList(postMgr.fetchPosts());
    }

    @GetMapping("/posts/create")
    public String showCreatePostForm(Model viewModel) {
        viewModel.addAttribute("post", new ViewModelPost());
        return "/posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@Valid @ModelAttribute("post") ViewModelPost post, BindingResult validation, Model viewModel, HttpServletRequest request) {

        System.out.println(post.getTitle());
        System.out.println(post.getBody());

        if(validation.hasErrors()) {
            System.out.println("get here errors");
            viewModel.addAttribute("hasErrors", validation.hasErrors());
            return "/posts/create";
        }
//        ViewModelUser user = (ViewModelUser) request.getSession().getAttribute("user");
        ViewModelUser user = userMgr.findById((long) 1);
        post.setUser(user);
        post.setDate(LocalDateTime.now());
        postMgr.save(post);
        return "redirect:/";
    }

    @GetMapping("/posts/{id}/{title}")
    public String showPostPage(@PathVariable(name="id") long id, Model viewModel) {
        System.out.println("get here");
        ViewModelPost post = postMgr.findById(id);
        System.out.println("post: " + post);
        System.out.println("post.user: " + post.getUser());
        viewModel.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/{id}/edit")
    public String showEditPostPage(@PathVariable(name="id") long id, Model viewModel) {
        ViewModelPost post = postMgr.findById(id);
        viewModel.addAttribute("post", post);
        return "posts/edit";
    }

    @PostMapping("/posts/edit")
    public String editPost(@Valid @ModelAttribute("post") ViewModelPost post,
                           BindingResult validation, Model viewModel) {

        ViewModelPost existingPost = postMgr.findById(post.getId());

        if(validation.hasErrors()) {
            viewModel.addAttribute("hasErrors", validation.hasErrors());
            return "posts/edit";
        }

        post.setDate(existingPost.getDate());
        post.setUser(existingPost.getUser());
//        postMgr.update(post);
        return "redirect:/profile";
    }

    //    =============== post votes ================

//    @PostMapping("/posts/{id}/{type}")
//    public ViewModelPost vote(@PathVariable(name="id") long id, @PathVariable(name="type") String vote, HttpServletRequest request) {

//        Post post = postSvc.findOne(id);
//        User user = (User) request.getSession().getAttribute("user");
//
//        System.out.println("post: " + post);
//        System.out.println("user: " + user);
//        System.out.println("vote type: " + vote);
//
//        if (vote.equalsIgnoreCase("upvote")) {
//            post.addVote(PostVote.up(post, user));
//            postVoteSvc.savePostVote(PostVote.up(post, user));
//            System.out.println("up");
//        } else {
//            post.addVote(PostVote.down(post, user));
//            postVoteSvc.savePostVote(PostVote.down(post, user));
//            System.out.println("down");
//        }
//
//        System.out.println("get here: " + post.getPostVotes());
//        return post;
//    }
}
