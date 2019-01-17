package com.trainingapp.trainingapp_web.controllers;

import com.trainingapp.trainingapp_web.models.ViewModelPost;
import com.trainingapp.trainingapp_web.services.PostManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PostsController {

    private PostManager postMgr;

    @Autowired
    public PostsController(PostManager postMgr) {
        this.postMgr = postMgr;
    }

    @GetMapping("/")
    public String index() {
        System.out.println("get to Home Page");
        return "index";
    }

    @GetMapping("/posts/create")
    public String showCreatePostForm(Model viewModel) {
        viewModel.addAttribute("post", new ViewModelPost());
        return "/posts/create";
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
}
