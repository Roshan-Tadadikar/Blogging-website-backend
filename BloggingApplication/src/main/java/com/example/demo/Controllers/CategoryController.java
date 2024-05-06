package com.example.demo.Controllers;

import com.example.demo.dto.Categorydto;
import com.example.demo.dto.Postdto;
import com.example.demo.services.CategoryService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/apis/categories")
@Slf4j
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Categorydto> createCategory(@Valid @RequestBody Categorydto category){
        log.info("Inside getCategory");
        Categorydto newCat = categoryService.createCategory(category);
        return  new ResponseEntity<>(newCat, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Categorydto> updateCategory(@PathVariable(name = "id") Integer id ,@Valid @RequestBody Categorydto category){
        log.info("Inside updateCategory");
        Categorydto newCat = categoryService.updateCategory(category,id);
        return  new ResponseEntity<>(newCat, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Categorydto> getCategory(@PathVariable(name = "id") Integer id){
        log.info("Inside getCategory");
        Categorydto newCat = categoryService.getCategory(id);
        return  new ResponseEntity<>(newCat, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Categorydto>> getAllCategory(){
        log.info("Inside getAllCategory");
        List<Categorydto> list = categoryService.getAllCategory();
        return  new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteCategory(@PathVariable(name = "id") Integer id){
        log.info("Inside deleteCategory");
        categoryService.deleteCategory(id);
        Map<String, String> map = new HashMap<>();
        map.put("Message", "Category with id = "+id+" deleted successfully!");
        return new ResponseEntity(map, HttpStatus.OK);
    }


}
