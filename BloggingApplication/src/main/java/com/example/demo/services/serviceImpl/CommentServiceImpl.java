package com.example.demo.services.serviceImpl;

import com.example.demo.Models.Comment;
import com.example.demo.Models.Posts;
import com.example.demo.dto.CommentDto;
import com.example.demo.repositories.CommentRepository;
import com.example.demo.repositories.PostRepository;
import com.example.demo.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PostRepository postRepository;

    @Override
    public CommentDto addComment(CommentDto comment, Integer postId) {
        Comment comment1 = modelMapper.map(comment, Comment.class);
        Optional<Posts> optionalPosts = postRepository.findById(postId);
        if(optionalPosts!=null){
            comment1.setPost(optionalPosts.get());
        }
        Comment updatedComment = commentRepository.save(comment1);
        if(updatedComment!=null){
            return modelMapper.map(updatedComment,CommentDto.class);
        }

            return null;
    }

    @Override
    public CommentDto updateComment(Integer id, CommentDto commentDto) {
        Optional<Comment> comment = commentRepository.findById(id);
        Comment actualComment = comment.get();
        if(actualComment!=null){
            if(commentDto.getComment()!=null)actualComment.setComment(commentDto.getComment());
        }
        Comment updatedComment = commentRepository.save(actualComment);
        if(updatedComment != null){
            return modelMapper.map(updatedComment, CommentDto.class);
        }

        return  null;

    }

    @Override
    public CommentDto getComment(Integer id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if(comment.get()!=null){
            CommentDto commentDto = modelMapper.map(comment.get(), CommentDto.class);
            return commentDto;
        }

        return null;
    }

    @Override
    public List<CommentDto> getCommentByPostId(Integer postId) {
       List<Comment> comments = commentRepository.findByPostId(postId);
       List<CommentDto> listOfComments = new ArrayList<>();
       for (Comment comment : comments){
           listOfComments.add(modelMapper.map(comment, CommentDto.class));
       }
       return listOfComments;
    }

    @Override
    public void deleteComment(Integer id) {
        commentRepository.deleteById(id);
    }
}
