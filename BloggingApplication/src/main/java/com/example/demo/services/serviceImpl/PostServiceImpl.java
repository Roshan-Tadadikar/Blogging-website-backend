package com.example.demo.services.serviceImpl;

import com.example.demo.Models.Category;
import com.example.demo.Models.Posts;
import com.example.demo.Models.User;
import com.example.demo.dto.PostResponse;
import com.example.demo.dto.Postdto;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.PostRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.PostsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostsService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Postdto createPost(Postdto post, Integer userId, Integer categoryId) {
        Posts posts = modelMapper.map(post, Posts.class);
        Optional<User> user = userRepository.findById(userId);
        Optional<Category> category = categoryRepository.findById(categoryId);
        if(user.get() != null)posts.setUser(user.get());
        if(category.get() != null)posts.setCategory(category.get());
        Posts createdPost = postRepository.save(posts);
        return modelMapper.map(createdPost, Postdto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public Postdto getPost(Integer postId) {
       Optional<Posts> foundPost = postRepository.findById(postId);
       if (foundPost.get() != null){
           return modelMapper.map(foundPost.get(), Postdto.class);
       }

       return  null;
    }

    @Override
    public PostResponse getAllPosts(Integer pageSize, Integer pageNumber, String sortBy, String orderBy) {
        Pageable pages = PageRequest.of(pageNumber,pageSize,Sort.by(orderBy.equals("asc")? Sort.Direction.ASC: Sort.Direction.DESC,sortBy));
        Page<Posts> list = postRepository.findAll(pages);
        List<Postdto> requiredList = new ArrayList<>();
        for(Posts p : list.getContent()){
            requiredList.add(modelMapper.map(p, Postdto.class));
        }
        PostResponse requiredResponse = new PostResponse();
        requiredResponse.setPosts(requiredList);
        requiredResponse.setPageNumber(list.getNumber());
        requiredResponse.setPageSize(list.getSize());
        requiredResponse.setTotalElements(list.getTotalPages());
        requiredResponse.setLastPage(list.isLast());
        return requiredResponse;
    }

    @Override
    public Postdto updatePost(Integer postId, Postdto post) {
        Optional<Posts> foundPost = postRepository.findById(postId);
        Posts oldPost = foundPost.get();
        if(oldPost != null){
            if(post.getTitle() != null) oldPost.setTitle(post.getTitle());
            if(post.getContent() != null) oldPost.setContent(post.getContent());
            if(post.getImageLink() != null) oldPost.setImageLink(post.getImageLink());
            if(post.getCategory() != null)oldPost.setCategory(modelMapper.map(post.getCategory(), Category.class));
            if(post.getUser() != null)oldPost.setUser(modelMapper.map(post.getUser(),User.class));
            Posts updatedPost = postRepository.save(oldPost);
            return modelMapper.map(updatedPost, Postdto.class);
        }

        return  null;
    }

    @Override
    public List<Postdto> findPostByUserId(Integer userId) {
        List<Posts> posts = postRepository.findByUserId(userId);
        List<Postdto> list = new ArrayList<>();
        if(posts != null){
            for(Posts post : posts){
                list.add(modelMapper.map(post, Postdto.class));
            }
        }
        return list;
    }


    @Override
    public List<Postdto> findPostByCategoryId(Integer categoryId) {
        List<Posts> posts = postRepository.findByCategoryId(categoryId);
        List<Postdto> list = new ArrayList<>();
        if(posts != null){
           for(Posts post : posts){
               list.add(modelMapper.map(post, Postdto.class));
           }
        }
        return list;
    }

    @Override
    public List<Postdto> findBySearch(String search){
        List<Posts> foundPosts = postRepository.findByTitleContaining(search);
        List<Postdto> list = new ArrayList<>();
        if(foundPosts!=null){
            for (Posts p : foundPosts){
                list.add(modelMapper.map(p, Postdto.class));
            }
        }

        return list;
    }


}
