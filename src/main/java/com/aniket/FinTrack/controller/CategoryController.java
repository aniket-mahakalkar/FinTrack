package com.aniket.FinTrack.controller;

import com.aniket.FinTrack.dto.CategoryDTO;
import com.aniket.FinTrack.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class CategoryController {

    private final CategoryService categoryService;


    @PostMapping("/categories")
    public ResponseEntity<CategoryDTO> saveCategory(@RequestBody CategoryDTO categoryDTO){

        CategoryDTO savedCategory = categoryService.saveCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }
}
