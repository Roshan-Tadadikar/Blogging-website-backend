package com.example.demo.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


@Data
public class Categorydto {

    @Id
    private Integer categoryId;

    @NotEmpty
    private String categoryTitle;

    @NotEmpty
    private String categoryDescription;


}
