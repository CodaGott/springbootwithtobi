package com.blogapp.data.repository;

import com.blogapp.data.models.Author;
import com.blogapp.data.models.Post;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
@SpringBootTest
@Sql(scripts = {"classpath:db/insert.sql"})
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void savePostToDBTest(){
        Post post = new Post();

        post.setTitle("My post title");
        post.setContent("This is my first post");
        post.setCoverImageUrl("post image");

        log.info("Created a blog post --> {}", post);
        postRepository.save(post);
        assertThat(post.getPostId()).isNotNull();

    }

    @Test
    void throwExceptionWhenPostWithSameTitleIsCreated(){
        Post post = new Post();

        post.setTitle("My post title");
        post.setContent("This is my first post");
        post.setCoverImageUrl("post image");

        log.info("Created a blog post --> {}", post);
        postRepository.save(post);
        assertThat(post.getPostId()).isNotNull();


        Post post2 = new Post();

        post2.setTitle("My post title");
        post2.setContent("This is my first post");
        post2.setCoverImageUrl("post image");

        log.info("Created a blog post --> {}", post2);
        assertThrows(DataIntegrityViolationException.class, ()-> postRepository.save(post2));
    }
    @Test
    void whenPostIsSaved_AuthorIsSaved(){
        Post post = new Post();
        Author author = new Author();

        post.setTitle("My post title");
        post.setContent("This is my first post");

        log.info("Created a blog post --> {}", post);

        author.setFirstName("John");
        author.setLastName("Doe");
        author.setPhoneNumber("093737");
        author.setEmail("john@mail.com");

        post.setAuthor(author);
        author.addPost(post);

        postRepository.save(post);
        log.info("Blog Post after saving --> {}", post);
    }

    @Test
    void findAllPostInTheDBTest(){
        List<Post> existingPosts = postRepository.findAll();
        assertThat(existingPosts).isNotNull();
        assertThat(existingPosts).hasSize(5);
    }

}