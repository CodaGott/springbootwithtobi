package com.blogapp.data.repository;

import com.blogapp.data.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Post findPostByTitle(String postTitle);

    Post findByTitle(String title);

    @Query("SELECT p from blog_post p where p.title = ?1")
    Post findByPostTitle(String title);


    List<Post> findByOrderByDateCreatedDesc();

//    List<Post> findPostInDescOrder();
}
