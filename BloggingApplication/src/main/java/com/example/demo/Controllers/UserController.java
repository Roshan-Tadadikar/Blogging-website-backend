package com.example.demo.Controllers;

import com.example.demo.dto.Userdto;
import com.example.demo.services.UserService;
import com.example.demo.services.serviceImpl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/apis/users")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Userdto> createUser(@Valid  @RequestBody Userdto userdto){
        log.info("Inside createUser controller"+userdto);
        Userdto createdUser = userService.createUser(userdto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }



    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<Userdto> getUser(@RequestParam("id") Integer id){
        log.info("Inside getUser controller");
        Userdto userdto = userService.getUserById(id);
        return new ResponseEntity<>(userdto, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<Userdto>> getAllUser(){
        log.info("Inside getAllUser controller");
       List<Userdto> list = userService.getAllUsers();
       return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<Userdto> updateUser(@Valid @RequestParam("id") Integer id, @RequestBody Userdto userdto){
        log.info("Inside updateUser controller"+userdto+" id ==> "+id);
        Userdto updatedUser = userService.updateUser(userdto, id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public ResponseEntity<Userdto> deleteUser(@RequestParam("id") Integer id){
        log.info("Inside deleteUser controller");
        userService.deleteUser(id);
        return new ResponseEntity(Map.of("message","Message deleted successfully!!"), HttpStatus.OK);
    }
}
