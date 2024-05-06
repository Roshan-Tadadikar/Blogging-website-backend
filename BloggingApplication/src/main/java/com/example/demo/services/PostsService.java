package com.example.demo.services;

import com.example.demo.dto.PostResponse;
import com.example.demo.dto.Postdto;

import java.util.List;

public interface PostsService {

    Postdto createPost(Postdto post, Integer userIn, Integer catId);
    void deletePost(Integer postId);
    Postdto getPost(Integer postId);
    PostResponse getAllPosts(Integer pageSize, Integer pageNumber, String sortBy, String orderBy);
    Postdto updatePost(Integer postId, Postdto post);
    List<Postdto> findPostByUserId(Integer userId);
    List<Postdto> findPostByCategoryId(Integer categoryId);
    List<Postdto> findBySearch(String search);
}
