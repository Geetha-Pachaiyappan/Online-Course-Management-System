package com.example.Online.Course.Management.System.controller;

import com.example.Online.Course.Management.System.dto.CategoryDto;
import com.example.Online.Course.Management.System.entity.Category;
import com.example.Online.Course.Management.System.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<List<CategoryDto>> saveAllCategories(@RequestBody List<CategoryDto> categoryList){
        return new ResponseEntity<>(categoryService.saveAllCategories(categoryList), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategory(), HttpStatus.OK);
    }
}
