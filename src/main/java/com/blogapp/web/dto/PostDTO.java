package com.blogapp.web.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PostDTO {

    private String title;

    private String content;

    private MultipartFile imageFile;

}
