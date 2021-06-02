package com.blogapp.web.controllers;

import com.blogapp.service.post.PostService;
import com.blogapp.web.dto.PostDTO;
import com.blogapp.web.exceptions.PostObjectIsNullException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping("/posts")
public class PostController {

    @Autowired
    PostService postServiceImpl;

    @GetMapping("")
    public String getIndex(){
        return "index";
    }

    @GetMapping("/create")
    public String getPostForm(Model model){
        model.addAttribute("post", new PostDTO());

        return "create";
    }

    @PostMapping("/save")
    public String savePost(@ModelAttribute @Valid PostDTO postDTO){
        log.info("Post dto received --> {}", postDTO);

        try{
            postServiceImpl.savePost(postDTO);
        }catch (PostObjectIsNullException pe){
            log.info("Exception occured --> {}", pe.getMessage());
        }
        return "redirect:/posts";
    }
}
