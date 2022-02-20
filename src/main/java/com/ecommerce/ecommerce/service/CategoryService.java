package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.model.Category;
import com.ecommerce.ecommerce.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    public void createCategory(Category category) {
        categoryRepo.save(category);
    }

    public List<Category> listCategory()
    {
        return categoryRepo.findAll();
    }

    public void editCategory(int categoryId, Category updateCategory) {
        Category category = categoryRepo.getById(categoryId);
        category.setCategoryName(updateCategory.getCategoryName());
        category.setDescription(updateCategory.getDescription());
        category.setImageURL(updateCategory.getImageURL());
        category.setProducts(updateCategory.getProducts());
        categoryRepo.save(category);
    }

    public boolean findById(int categoryId) {
        return categoryRepo.findById(categoryId).isPresent();
    }

//    public void deleteCategory(Integer categoryId) {
//        Optional<Category> optionalCategory = categoryRepo.findById(categoryId);
//
//        if(optionalCategory.isEmpty()){
//            throw new CustomException("Invalid category id: " + categoryId);
//        }
//
//        Category category = optionalCategory.get();
//
//        categoryRepo.delete(category);
//    }

}
