package com.example.demo.dto;

import com.example.demo.Models.Category;
import com.example.demo.Models.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.util.Date;

@Data
public class Postdto {


    private Integer id;
    @URL(message = "Please enter valid image Link")
    @NotEmpty
    private String imageLink;
    @NotEmpty
    @Size(min = 5, message = "Please enter a title of length of atleast 5")
    private String title;
    @NotEmpty
    @Size(max = 250, message = "Please enter characters not more than 250")
    private String content;
    Userdto user;
    Categorydto category;
    private Date addedDate;

}