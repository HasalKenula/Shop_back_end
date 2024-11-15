package com.ijse.springintro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.springintro.entity.Category;
import com.ijse.springintro.service.CategoryService;

@RestController
@CrossOrigin(origins = "*")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<?>  createCategory(@RequestBody Category category){
        if(category.getName()==null|| category.getName()==""){
            return ResponseEntity.status(400).body("Please Enter the valid name.");
        }

        try{
            Category createCategory=categoryService.createCategory(category);
            return ResponseEntity.status(201).body(createCategory);
        }catch(Exception e){
            return ResponseEntity.status(201).body(e.getMessage());
        }
    }

    @GetMapping("/category")
    public ResponseEntity<List<Category>> getAllCategory(){
        List<Category> category=categoryService.getAllCategory();
        return ResponseEntity.status(200).body(category);
    }

   
    
}
