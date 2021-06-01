package com.blogapp.web.dto;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
public class PostDTO {

    @NotNull(message = "Title can't be empty")
    private String title;

    @NotNull(message = "Content can't be empty")
    private String content;

    private MultipartFile imageFile;

}
