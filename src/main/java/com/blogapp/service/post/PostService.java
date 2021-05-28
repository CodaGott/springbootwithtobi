package com.blogapp.service.post;

import com.blogapp.data.models.Comment;
import com.blogapp.data.models.Post;
import com.blogapp.web.dto.PostDTO;

import java.util.List;

public interface PostService {

    Post savePost(PostDTO postDTO);

    List<Post> findAllPosts();

    Post updatePost(PostDTO postDTO);

    void deletePost();

    Post findPostById(Integer postId);

    Post addCommentToPost(Integer postId, Comment comment);

}
