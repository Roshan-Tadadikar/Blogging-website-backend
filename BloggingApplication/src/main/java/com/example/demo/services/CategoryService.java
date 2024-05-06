package com.example.demo.services;

import com.example.demo.dto.Categorydto;

import java.util.List;

public interface CategoryService {

    Categorydto createCategory(Categorydto category);
    Categorydto getCategory(Integer id);
    Categorydto updateCategory(Categorydto category, Integer id);
    List<Categorydto> getAllCategory();
    void deleteCategory(Integer id);

}
