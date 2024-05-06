package com.example.demo.services;

import com.example.demo.dto.Userdto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    Userdto createUser(Userdto userdto);
    Userdto updateUser(Userdto userdto, Integer userId);
    Userdto getUserById(Integer userId);
    List<Userdto> getAllUsers();
    void deleteUser(Integer userId);


}
