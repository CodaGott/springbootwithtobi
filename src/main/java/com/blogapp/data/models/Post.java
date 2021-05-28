package com.blogapp.data.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity(name = "blog_post")
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer postId;

    @Column(length = 50, nullable = false, unique = true)
    private String title;

    @Column(length = 500, nullable = false)
    private String content;

    @Column()
    private String coverImageUrl;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn
    private Author author;

    @CreationTimestamp
    private LocalDate dataCreated;

    @UpdateTimestamp
    private LocalDate dateModified;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Comment> comments;

    public void addComment(Comment... newComment){
        if(this.comments == null) {
            this.comments = new ArrayList<>();
        }
        this.comments.addAll(Arrays.asList(newComment));
    }

}
