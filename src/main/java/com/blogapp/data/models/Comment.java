package com.blogapp.data.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "comment")
@Data
public class Comment {
    @Id
    @GeneratedValue
    private UUID commentId;

    private String authorName;

    @Column(nullable = false, length = 150)
    private String content;

    @CreationTimestamp
    private LocalDate dateCreated;

    public Comment(String authorName, String content){
        this.content = content;
        this.authorName = authorName;
    }

    public Comment(){}
}
