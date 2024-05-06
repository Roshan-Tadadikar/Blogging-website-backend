package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class Userdto {
    private Integer id;
    @URL(message = "Please enter a valid URL")
    private String imageLink;
    @NotEmpty(message = "Please write something about yourself!")
    private String about;
    @Email(message = "Please enter a valid email")
    private String email;
    @NotNull
    @Size(min = 5, max = 20, message = "Please enter a valid name")
    private String name;
    @Size(min = 8, max = 20,message = "Please enter a password of length 8 or more!")
    @NotNull
    @JsonIgnore
    private String password;
}
