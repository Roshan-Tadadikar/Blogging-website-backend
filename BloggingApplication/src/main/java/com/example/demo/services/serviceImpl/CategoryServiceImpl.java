package com.example.demo.services.serviceImpl;

import com.example.demo.Models.Category;
import com.example.demo.dto.Categorydto;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
   CategoryRepository categoryRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public Categorydto createCategory(Categorydto category) {
        Category newCat = modelMapper.map(category, Category.class);
        Category updatedCat = categoryRepository.save(newCat);
        return modelMapper.map(updatedCat, Categorydto.class);
    }

    @Override
    public Categorydto getCategory(Integer id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.get() != null){
            return  modelMapper.map(category.get(), Categorydto.class);
        }

        return null;
    }

    @Override
    public Categorydto updateCategory(Categorydto category, Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.get() != null){
            Category newCat = optionalCategory.get();
            if(category.getCategoryTitle() != null) newCat.setCategoryTitle(category.getCategoryTitle());
            if(category.getCategoryDescription() != null) newCat.setCategoryDescription(category.getCategoryDescription());
            Category updatedCategory = categoryRepository.save(newCat);
            return modelMapper.map(updatedCategory, Categorydto.class);
        }

        return null;
    }

    @Override
    public List<Categorydto> getAllCategory() {
        List<Category> list = categoryRepository.findAll();
        List<Categorydto> requiredList = new ArrayList<>();
        for(Category c : list){
            requiredList.add(modelMapper.map(c, Categorydto.class));
        }

        return requiredList;
    }

    @Override
    public void deleteCategory(Integer id){
        categoryRepository.deleteById(id);
    }
}
