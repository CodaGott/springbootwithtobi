package com.blogapp.service.post;

import com.blogapp.data.models.Comment;
import com.blogapp.data.models.Post;
import com.blogapp.data.repository.PostRepository;
import com.blogapp.web.dto.PostDTO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post savePost(PostDTO postDTO) {
        Post post = new Post();

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(postDTO, post);

        log.info("Post Object after mapping -->{}", post);

//        post.setContent(postDTO.getContent());
//        ModelMap modelMap = new ModelMap();
//        post.setTitle(postDTO.getTitle());
//        post.setCoverImageUrl(postDTO.getImageFile());

        return postRepository.save(post);
    }

    @Override
    public List<Post> findAllPosts() {
        return null;
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
