package com.ecommerce.ecommerce.controller;


import com.ecommerce.ecommerce.common.ApiResponse;
import com.ecommerce.ecommerce.dto.ProductDTO;
import com.ecommerce.ecommerce.model.Category;
import com.ecommerce.ecommerce.repository.CategoryRepo;
import com.ecommerce.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryRepo categoryRepo;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDTO productDTO)
    {
        Optional<Category> optionalCategory = categoryRepo.findById(productDTO.getCategoryId());

        if(!optionalCategory.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(false, "Category does not exist"), HttpStatus.BAD_REQUEST);
        }

        productService.createProduct(productDTO, optionalCategory.get());
        return new ResponseEntity<>(new ApiResponse(true, "Product has been successfully added!"), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductDTO>> getProducts() {

        List<ProductDTO> productDTOList = productService.getAllProducts();
        return new ResponseEntity<>(productDTOList, HttpStatus.OK);
    }

    @PostMapping("/update/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productId") Integer productId, @RequestBody ProductDTO productDTO) throws Exception {

        Optional<Category> optionalCategory = categoryRepo.findById(productDTO.getCategoryId());

        if(!optionalCategory.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(false, "Category does not exist"), HttpStatus.BAD_REQUEST);
        }

        productService.updateProduct(productDTO, productId);
        return new ResponseEntity<>(new ApiResponse(true, "Product has been successfully updated!"), HttpStatus.OK);
    }

//    @DeleteMapping("/delete/{productId}")
//    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable("productId") Integer productId){
//
//        productService.deleteProduct(productId);
//
//        return new ResponseEntity<>(new ApiResponse(true, "Successfully deleted product "), HttpStatus.OK);
//    }



}
