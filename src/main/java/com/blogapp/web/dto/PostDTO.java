package com.blogapp.web.dto;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
public class PostDTO {

    @NotBlank(message = "Title can't be empty")
    private String title;

    @NotBlank(message = "Content can't be empty")
    private String content;

    private MultipartFile imageFile;

}
