package com.example.Online.Course.Management.System.service;

import com.example.Online.Course.Management.System.dto.CategoryDto;
import com.example.Online.Course.Management.System.entity.Category;
import com.example.Online.Course.Management.System.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Date;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private ModelMapper modelMapper;

    public List<CategoryDto> saveAllCategories(List<CategoryDto> categoryDto){
        List<Category> categoryList  = categoryDto.stream()
                .map(categoryDto1 -> modelMapper.map(categoryDto1, Category.class)).toList();

        List<Category> savedCategories = categoryRepo.saveAll(categoryList);

        return savedCategories.stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .toList();
    }

    public List<CategoryDto> getAllCategory(){
        List<Category> categoryList = categoryRepo.findAll();
        return categoryList.stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .toList();
    }
}
