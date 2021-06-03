package com.blogapp.web.controllers;

import com.blogapp.data.models.Post;
import com.blogapp.service.post.PostService;
import com.blogapp.web.dto.PostDTO;
import com.blogapp.web.exceptions.PostObjectIsNullException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/posts")
public class PostController {

    @Autowired
    PostService postServiceImpl;

    @GetMapping("")
    public String getIndex(Model model){
        List<Post> postList = postServiceImpl.findAllPosts();
        model.addAttribute("postList", postList);

        return "index";
    }

    @GetMapping("/create")
    public String getPostForm(Model model){
        model.addAttribute("postDTO", new PostDTO());
        model.addAttribute("error", false);
        return "create";
    }

    @PostMapping("/save")
    public String savePost(@ModelAttribute @Valid PostDTO postDTO, BindingResult result, Model model){
        log.info("Post dto received --> {}", postDTO);

        if (result.hasErrors()){
            return "create";
        }

        try{
            postServiceImpl.savePost(postDTO);
        }catch (PostObjectIsNullException pe){
            log.info("Exception occurred --> {}", pe.getMessage());
        }catch (DataIntegrityViolationException dx){
            log.info("Constrain exception occurred --> {}", dx.getMessage());
            model.addAttribute("error", true);
            model.addAttribute("errorMessage", "Title already exists");
            return "create";
        }
        return "redirect:/posts";
    }

    @ModelAttribute
    public void createPostModel(Model model){
        model.addAttribute("postDTO", new PostDTO());
    }
}
