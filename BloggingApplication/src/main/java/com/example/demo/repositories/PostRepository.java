package com.example.demo.repositories;

import com.example.demo.Models.Posts;
import com.example.demo.dto.Postdto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Posts, Integer> {

    List<Posts> findByUserId(Integer userId);
    List<Posts> findByCategoryId(Integer categoryId);
    List<Posts> findByTitleContaining(String search);

}
