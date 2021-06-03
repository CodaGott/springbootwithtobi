package com.blogapp.service.post;

import com.blogapp.data.models.Comment;
import com.blogapp.data.models.Post;
import com.blogapp.data.repository.PostRepository;
import com.blogapp.service.cloud.CloudStorageService;
import com.blogapp.web.dto.PostDTO;
import com.blogapp.web.exceptions.PostDoesNotExistException;
import com.blogapp.web.exceptions.PostObjectIsNullException;
import com.cloudinary.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

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
                                "public_id", "blogapp/" + extractFileName(Objects.requireNonNull(postDTO.getImageFile().getName()))
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
    public Post findPostById(Integer postId) throws PostDoesNotExistException {
//        Post post = new Post();
//        if (post.getPostId().equals(postId)){
//            return postRepository.findById(postId).get();
//        }else{
//            throw new NoSuchElementException("Post does not exist");
//        }

        if (postId == null){
            throw new NullPointerException("Post Id can't be null");
        }

        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()){
            return post.get();
        }else{
            throw new PostDoesNotExistException("Post with Id --> {}");
        }



        // Optional <Post> post = postrepo.findById(postId)

    }

    @Override
    public Post addCommentToPost(Integer postId, Comment comment) {
        return null;
    }

    @Override
    public List<Post> findPostInDescOrder() {
        return postRepository.findByOrderByDateCreatedDesc();
    }

    private String extractFileName(String filename){
        return filename.split("\\.")[0];
    }
}
