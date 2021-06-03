package com.blogapp.service.cloud;

import com.blogapp.web.dto.PostDTO;
import com.cloudinary.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class CloudinaryCloudStorageServiceImplTest {

    @Autowired @Qualifier("cloudinary")
    private CloudStorageService cloudStorageService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void uploadImage() throws IOException {

        // define a file
        File file = new File("C:\\Users\\DOZIE\\Desktop\\LESSON\\blogapp\\src\\main\\resources\\static" +
                "\\asset\\images\\amazon.png");

        assertThat(file.exists()).isTrue();

        Map<Object, Object> params = new HashMap<>();

        params.put("folder", "blogapp");
        params.put("overwrite", "true");

        try{
            cloudStorageService.uploadImage(file, params);
        }catch (IOException | RuntimeException e){
            log.error("Error Occured --> {}", e.getMessage());
        }
    }


    @Test
    void uploadMultiPartImage() throws IOException {

        // define a file
//        File file = new File("C:\\Users\\DOZIE\\Desktop\\LESSON\\blogapp\\src\\main\\resources\\static" +
//                "\\asset\\images\\amazon.png");
//
//        assertThat(file.exists()).isTrue();
//
//        Map<Object, Object> params = new HashMap<>();
//
//        params.put("folder", "blogapp");
//        params.put("overwrite", "true");
//
//        try{
//            cloudStorageService.uploadImage(file, params);
//        }catch (IOException | RuntimeException e){
//            log.error("Error Occured --> {}", e.getMessage());
//        }

        PostDTO postDTO = new PostDTO();
        postDTO.setContent("Test");
        postDTO.setTitle("test");

        Path path = Paths.get("C:\\Users\\DOZIE\\Desktop\\LESSON\\blogapp\\src\\main\\resources\\static" +
                "\\asset\\images\\amazon.png");

        MultipartFile multipartFile = new MockMultipartFile("images.jpeg", "images.jpeg", "img/jpeg",Files.readAllBytes(path));

        log.info("Multipart object created --> {}", multipartFile);
        assertThat(multipartFile).isNotNull();

        postDTO.setImageFile(multipartFile);

        log.info("File name --> {}", postDTO.getImageFile().getOriginalFilename());
        cloudStorageService.uploadImage(multipartFile, ObjectUtils.asMap(
                "public_id", "blogapp/" + extractFileName(Objects.requireNonNull(postDTO.getImageFile().getOriginalFilename()
                ))));

        assertThat(postDTO.getImageFile().getOriginalFilename()).isEqualTo("images.jpeg");
    }

    private String extractFileName(String filename){
        return filename.split("\\.")[0];
    }
}