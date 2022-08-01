package com.mvpt.controller.rest;

import com.mvpt.model.Category;
import com.mvpt.model.dto.CategoryDTO;
import com.mvpt.repository.CategoryRepository;
import com.mvpt.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryRestController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<CategoryDTO> categories = categoryService.getAllCategoryDTO();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
