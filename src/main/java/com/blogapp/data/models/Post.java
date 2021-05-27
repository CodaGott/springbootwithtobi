package com.blogapp.data.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn
    private Author author;

    @CreationTimestamp
    private LocalDate dataCreated;

    @UpdateTimestamp
    private LocalDate dateModified;

    @OneToMany
    private List<Comment> comments;

}
