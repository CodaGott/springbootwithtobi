package com.blogapp.service.cloud;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import static org.assertj.core.api.Assertions.*;

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
}