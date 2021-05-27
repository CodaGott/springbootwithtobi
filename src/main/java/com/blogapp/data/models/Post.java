package com.blogapp.data.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "blog_post")
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer postId;

    @Column(length = 50, nullable = false)
    private String tittle;

    @Column(length = 1000, nullable = false)
    private String content;

    @Column()
    private String coverImageUrl;

    @ManyToOne
    private Author author;

    private LocalDate dataCreated;

    private LocalDate dateModified;

    @OneToMany
    private List<Comment> comments;

}
