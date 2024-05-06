package com.example.demo.dto;

import com.example.demo.Models.Posts;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class CommentDto {
    private Integer id;
    private String comment;
}
