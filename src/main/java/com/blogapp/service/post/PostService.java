package com.blogapp.service.post;

import com.blogapp.data.models.Comment;
import com.blogapp.data.models.Post;
import com.blogapp.web.dto.PostDTO;
import com.blogapp.web.exceptions.PostDoesNotExistException;
import com.blogapp.web.exceptions.PostObjectIsNullException;

import java.util.List;

public interface PostService {

    Post savePost(PostDTO postDTO) throws PostObjectIsNullException;

    List<Post> findAllPosts();

    Post updatePost(PostDTO postDTO);

    void deletePost();

    Post findPostById(Integer postId) throws PostDoesNotExistException;

    Post addCommentToPost(Integer postId, Comment comment);

    List<Post> findPostInDescOrder();

}
