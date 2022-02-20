package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.common.ApiResponse;
import com.ecommerce.ecommerce.model.Category;
import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category) {
        categoryService.createCategory(category);
        return new ResponseEntity<>(new ApiResponse(true, "New category created!"), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public List<Category> listCategory()
    {
        return categoryService.listCategory();
    }

    @PostMapping("/update/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryId") int categoryId, @RequestBody Category category) {

        if(!categoryService.findById(categoryId)) {
            return new ResponseEntity<>(new ApiResponse(false, "Category does not exist"), HttpStatus.NOT_FOUND);
        }
        categoryService.editCategory(categoryId, category);
        return new ResponseEntity<>(new ApiResponse(true, "Category successfully updated!"), HttpStatus.OK);
    }

//    @DeleteMapping("/delete/{categoryId}")
//    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") Integer categoryId){
//
//        categoryService.deleteCategory(categoryId);
//
//        return new ResponseEntity<>(new ApiResponse(true, "Successfully deleted category "), HttpStatus.OK);
//
//    }
}
