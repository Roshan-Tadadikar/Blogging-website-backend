package com.example.demo.Controllers;

import com.example.demo.dto.CommentDto;
import com.example.demo.services.serviceImpl.CommentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/apis/")
public class CommentController {

    @Autowired
    CommentServiceImpl commentService;

    @PostMapping(value = "post/{postId}/comments")
    public ResponseEntity createComment(@PathVariable Integer postId, @RequestBody CommentDto commentDto){
        log.info("*** Inside create comment ***");
        CommentDto commentDto1 =commentService.addComment(commentDto, postId);
        return  new ResponseEntity(commentDto1, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/comment/{commentId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteComment(@PathVariable Integer commentId){
        log.info("*** Inside deleteComment ***");
        commentService.deleteComment(commentId);
        Map<String, String> map = new HashMap<>();
        map.put("Message", "Comment with id = "+commentId+" has been deleted successfully!");
        return  new ResponseEntity(map, HttpStatus.OK);
    }

    @RequestMapping(value = "/comment/update/{commentId}", method = RequestMethod.POST)
    public ResponseEntity udpateComment(@PathVariable Integer commentId, @RequestBody CommentDto commentDto){
        log.info("*** Inside create comment ***");
       CommentDto commentDto1 = commentService.updateComment(commentId, commentDto);
        return  new ResponseEntity(commentDto1, HttpStatus.OK);
    }

    @RequestMapping(value = "/comment/{commentId}",method = RequestMethod.GET)
    public ResponseEntity getComment(@PathVariable Integer commentId){
        log.info("*** Inside getComment ***");
        CommentDto commentDto1 = commentService.getComment(commentId);
        return  new ResponseEntity(commentDto1, HttpStatus.OK);
    }

    @GetMapping(value = "/comment/post/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentByPostIds(@PathVariable Integer postId){
        log.info("*** Inside getCommentByPostId ***");
        List<CommentDto> commentDto1 = commentService.getCommentByPostId(postId);
        return  new ResponseEntity(commentDto1, HttpStatus.OK);
    }


}
