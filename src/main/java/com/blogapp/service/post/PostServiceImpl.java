package com.blogapp.service.post;

import com.blogapp.data.models.Comment;
import com.blogapp.data.models.Post;
import com.blogapp.data.repository.PostRepository;
import com.blogapp.service.cloud.CloudStorageService;
import com.blogapp.web.dto.PostDTO;
import com.blogapp.web.exceptions.PostObjectIsNullException;
import com.cloudinary.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;

    @Autowired
    CloudStorageService cloudStorageService;

    @Override
    public Post savePost(PostDTO postDTO) throws PostObjectIsNullException {

        if (postDTO == null){
            throw  new PostObjectIsNullException("Post cannot be null");
        }

        Post post = new Post();


        if(postDTO.getImageFile() != null && !postDTO.getImageFile().isEmpty()){
//            Map<Object, Object> params = new HashMap<>();
//            params.put("public_id", "blogapp/" + postDTO.getImageFile().getName());
//            params.put("overwrite", true);
//            log.info("parameters --> {}", params);
            try {
                Map<?, ?> uploadResult =
                cloudStorageService.uploadImage(postDTO.getImageFile(),
                        ObjectUtils.asMap(
                                "public_id", "blogapp/" + postDTO.getImageFile().getName(),
                                "overwrite", true
                        ));
                post.setCoverImageUrl(String.valueOf(uploadResult.get("url")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());

//        log.info("Post ");


//        ModelMapper modelMapper = new ModelMapper();
//        modelMapper.map(postDTO, post);
//
//        log.info("Post Object after mapping -->{}", post);

//        post.setContent(postDTO.getContent());
//        ModelMap modelMap = new ModelMap();
//        post.setTitle(postDTO.getTitle());
//        post.setCoverImageUrl(postDTO.getImageFile());

        try{
            return postRepository.save(post);
        }catch (DataIntegrityViolationException ex){
            log.info("Exception occurred -->{}", ex.getMessage());
            throw ex;
        }

    }

    @Override
    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post updatePost(PostDTO postDTO) {
        return null;
    }

    @Override
    public void deletePost() {

    }

    @Override
    public Post findPostById(Integer postId) {
        return null;
    }

    @Override
    public Post addCommentToPost(Integer postId, Comment comment) {
        return null;
    }
}
