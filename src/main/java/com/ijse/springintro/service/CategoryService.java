package com.ijse.springintro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.springintro.entity.Category;

@Service
public interface CategoryService {
    Category createCategory(Category category);
    List<Category> getAllCategory();
    Category getCategoryById(Long id);
}
