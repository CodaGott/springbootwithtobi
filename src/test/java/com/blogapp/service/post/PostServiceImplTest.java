package com.blogapp.service.post;

import com.blogapp.data.models.Post;
import com.blogapp.data.repository.PostRepository;
import com.blogapp.web.dto.PostDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

class PostServiceImplTest {

    @Mock
    PostRepository postRepository;

    @InjectMocks
    PostService postServiceImpl = new PostServiceImpl();

    Post testPost;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testPost = new Post();
    }
    @Test
    void whenTheSaveMethodIsCalled_thenRepositoryIsCalledOnceTest(){
        when(postServiceImpl.savePost(new PostDTO())).thenReturn(testPost);
        postServiceImpl.savePost(new PostDTO());

        verify(postRepository.save(testPost), times(1));
    }
}