package com.blogapp.data.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String profession;

    @Column(unique = true)
    private String email;

    private String phoneNumber;

    @OneToMany
    private List<Post> posts;


}
