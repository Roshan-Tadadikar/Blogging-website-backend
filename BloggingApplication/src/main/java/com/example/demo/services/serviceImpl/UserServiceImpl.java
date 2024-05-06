package com.example.demo.services.serviceImpl;

import com.example.demo.Models.User;
import com.example.demo.dto.Userdto;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Userdto createUser(Userdto userdto) {
        User user  = userDtoToEntity(userdto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser= userRepository.save(user);
        if(savedUser!=null && savedUser.getId()>0){
            return userToUserDto(savedUser);
        }
        return  null;
    }

    @Override
    public Userdto updateUser(Userdto userdto, Integer userId) {
        User user  = userDtoToEntity(userdto);
        Optional<User> findUser = userRepository.findById(userId);
        if(findUser.get()!=null){
            User currentUser = findUser.get();
            if(userdto.getPassword()!=null)currentUser.setPassword(passwordEncoder.encode(user.getPassword()));
            if(userdto.getName()!=null)currentUser.setName(user.getName());
            if(userdto.getEmail()!=null)currentUser.setEmail(user.getPassword());
            if(userdto.getAbout()!=null)currentUser.setAbout(user.getAbout());
            if(userdto.getImageLink()!=null)currentUser.setImageLink(user.getImageLink());
            if(userdto.getAbout()!=null)currentUser.setAbout(user.getAbout());
            User updatedUser = userRepository.save(currentUser);
            return  modelMapper.map(updatedUser, Userdto.class);
        }

        return  null;
    }

    @Override
    public Userdto getUserById(Integer userId) {
        return userToUserDto(userRepository.findById(userId).get());
    }

    @Override
    public List<Userdto> getAllUsers() {
        List<User> listOfUsers = userRepository.findAll();
        List<Userdto> listOfUserDto =  new ArrayList<>();
        for(User user: listOfUsers){
            listOfUserDto.add(userToUserDto(user));
        }

        return listOfUserDto;
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

    public User userDtoToEntity(Userdto userdto){
        User user = modelMapper.map(userdto, User.class);
        return user;
    }

    public Userdto userToUserDto(User user){
        Userdto userdto = modelMapper.map(user,Userdto.class);
        return userdto;
    }
}
