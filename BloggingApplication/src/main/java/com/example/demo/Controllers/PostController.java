package com.example.demo.Controllers;

import com.example.demo.Config.AppConstants;
import com.example.demo.Models.Posts;
import com.example.demo.dto.PostResponse;
import com.example.demo.dto.Postdto;
import com.example.demo.services.serviceImpl.PostServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/apis/")
@Slf4j
public class PostController {

    @Autowired
    PostServiceImpl postService;

    @RequestMapping(value = "/user/{userId}/category/{categoryId}", method = RequestMethod.POST)
    public ResponseEntity<Postdto> createPost(@Valid @RequestBody Postdto posts,
                                              @PathVariable(name = "userId") Integer userId,
                                              @PathVariable(name="categoryId") Integer categoryId){
        log.info("*** Inside createPost ***");
        posts.setAddedDate(new Date());
        Postdto createdPost = postService.createPost(posts, userId, categoryId);
        return  new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @RequestMapping(value = "post/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deletePost(@PathVariable(name = "id") Integer id){
        log.info("*** Inside deletePost ***");
        postService.deletePost(id);
        Map<String, String> map = new HashMap<>();
        map.put("message","Post with id = "+id+" deleted Successfully!");
        return  new ResponseEntity(map, HttpStatus.OK);
    }

    @RequestMapping(value = "post/{id}", method = RequestMethod.GET)
    public ResponseEntity<Postdto> getPost(@PathVariable(name = "id") Integer id){
        log.info("*** Inside getPost ***");
        Postdto createdPost = postService.getPost(id);
        return  new ResponseEntity<>(createdPost, HttpStatus.OK);
    }

    @RequestMapping(value = "post/", method = RequestMethod.GET)
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(name = "pageNumber", required = false, defaultValue = AppConstants.PAGE_NUMBER)Integer pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = AppConstants.PAGE_SIZE) Integer pageSize,
            @RequestParam(name = "sortBy", required = false, defaultValue = AppConstants.SORT_BY) String sortBy,
            @RequestParam(name = "orderBy", required = false,defaultValue = AppConstants.PAGE_ORDER) String orderBy
    ){
        log.info("*** Inside getAllPosts ***");
      PostResponse response  = postService.getAllPosts(pageSize,pageNum,sortBy,orderBy);
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "post/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Postdto> updatePost(@Valid @RequestBody Postdto posts, @PathVariable(name = "id") Integer id){
        log.info("*** Inside updatePost ***");
        Postdto updatePost = postService.updatePost(id,posts);
        return  new ResponseEntity<>(updatePost, HttpStatus.OK);
    }

    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    public ResponseEntity getPostByUserId(@PathVariable(name="id") Integer id){
        log.info("Inside getPostByUserId");
        List<Postdto> posts = postService.findPostByUserId(id);
        if(posts != null ) return  new ResponseEntity(posts,HttpStatus.OK);
        Map<String, String> map = new HashMap<>();
        map.put("Message", "Post not found with user id = "+id);
        return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "category/{id}", method = RequestMethod.GET)
    public ResponseEntity getPostByCategoryId(@PathVariable(name="id") Integer id){
        log.info("Inside getPostByCategoryId");
        List<Postdto> posts = postService.findPostByCategoryId(id);
        if(posts != null ) return  new ResponseEntity(posts,HttpStatus.OK);
        Map<String, String> map = new HashMap<>();
        map.put("Message", "Post not found with category id = "+id);
        return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "post", method = RequestMethod.GET)
    public ResponseEntity getPostBySearch(@RequestParam(name = "search")String search){
        log.info("Inside getPostBySearch");
        List<Postdto> list = postService.findBySearch(search);
        return  new ResponseEntity(list, HttpStatus.OK);
    }

}
