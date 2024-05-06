package com.example.demo.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "posts")
@Data
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "imageLink", nullable = false)
    private String imageLink;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;
    @ManyToOne
    User user;

    @JoinColumn(name = "categoryId", nullable = false)
    @ManyToOne
    Category category;

    @Column(nullable = false)
    private Date addedDate;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Comment> comments;

}
