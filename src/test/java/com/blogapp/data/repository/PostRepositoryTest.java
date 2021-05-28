package com.blogapp.data.repository;

import com.blogapp.data.models.Author;
import com.blogapp.data.models.Comment;
import com.blogapp.data.models.Post;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    @Rollback(value = false)
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


    @Test
    @Transactional
    @Rollback(value = false)
    void testThatPostCanBeDeletedById(){

        Post savedPost = postRepository.findById(42).orElse(null);
        assertThat(savedPost).isNotNull();
        log.info("Post fetched from the database --> {}", savedPost);
        //delete post
        postRepository.deleteById(savedPost.getPostId());

        Post deletedPost =postRepository.findById(savedPost.getPostId()).orElse(null);
        assertThat(deletedPost).isNull();
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void updateSavedPostTest(){
        Post posUpdate = postRepository.findById(43).orElse(null);
        assertThat(posUpdate.getContent()).isEqualTo("Post Content 3");

        posUpdate.setContent("Hello, World!");

        postRepository.save(posUpdate);

        assertThat(posUpdate.getContent()).isEqualTo("Hello, World!");

        postRepository.save(posUpdate);
        assertThat(posUpdate.getContent()).isEqualTo("Hello, World!");
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void updatePostAuthorTest(){
        Post savedPost = postRepository.findById(41).orElse(null);
        assertThat(savedPost).isNotNull();
        log.info("Post fetched from the database --> {}", savedPost);

        Author author = new Author();
        author.setLastName("Brown");
        author.setFirstName("Blue");
        author.setPhoneNumber("09948");
        author.setEmail("Brown@mail.com");
        author.setProfession("Musician");

        savedPost.setAuthor(author);
        postRepository.save(savedPost);

        Post updatedPost = postRepository.findById(savedPost.getPostId()).orElse(null);
        assertThat(updatedPost).isNotNull();
        assertThat(updatedPost.getAuthor()).isNotNull();
        assertThat(updatedPost.getAuthor().getLastName()).isEqualTo("Brown");
    }


    @Test
    @Transactional
    @Rollback(value = false)
    void canAddCommentToExistingPost(){
        Post postToComment = postRepository.findById(41).orElse(null);
        assertThat(postToComment).isNotNull();
        assertThat(postToComment.getComments()).hasSize(0);

        Comment comment = new Comment("Peter bread","Insightful Post");
        Comment comment2 = new Comment("Nonso Pride","Nice Post");
        //map Post and comments
        postToComment.addComment(comment, comment2);

//        postToComment.setComments(postToComment.addComment(comment));

        postRepository.save(postToComment);

        Post commentPost = postRepository.findById(postToComment.getPostId()).orElse(null);

        assertThat(commentPost).isNotNull();
        assertThat(commentPost.getComments()).hasSize(2);

    }

}