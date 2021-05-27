package com.blogapp.data.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
public class Comment {
    @Id
    private Integer commentId;

    private String authorName;

    @Column(nullable = false, length = 150)
    private String content;

    private LocalDate dateCreated;
}