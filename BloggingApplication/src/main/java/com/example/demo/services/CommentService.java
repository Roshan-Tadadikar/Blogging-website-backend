package com.example.demo.services;

import com.example.demo.dto.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto addComment(CommentDto comment, Integer postId);
    CommentDto updateComment(Integer id, CommentDto commentDto);
    CommentDto getComment(Integer id);
    List<CommentDto> getCommentByPostId(Integer postId);
    void deleteComment(Integer id);
}
