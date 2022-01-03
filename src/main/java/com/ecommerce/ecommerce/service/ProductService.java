package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.ProductDTO;
import com.ecommerce.ecommerce.exceptions.CustomException;
import com.ecommerce.ecommerce.exceptions.ProductNotExistException;
import com.ecommerce.ecommerce.model.Category;
import com.ecommerce.ecommerce.model.Product;
import com.ecommerce.ecommerce.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    public void createProduct(ProductDTO productDTO, Category category) {

        Product product = new Product();

        product.setDescription(productDTO.getDescription());
        product.setImageURL(productDTO.getImageURL());
        product.setPrice(productDTO.getPrice());
        product.setName(productDTO.getName());
        product.setCategory(category);

        productRepo.save(product);
    }

    public ProductDTO getProductDTO(Product product){

        ProductDTO productDTO = new ProductDTO();

        productDTO.setDescription(product.getDescription());
        productDTO.setImageURL(product.getImageURL());
        productDTO.setPrice(product.getPrice());
        productDTO.setName(product.getName());
        productDTO.setCategoryId(product.getCategory().getId());
        productDTO.setId(product.getId());

        return productDTO;
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> allProducts = productRepo.findAll();
        List<com.ecommerce.ecommerce.dto.ProductDTO> productDTOList = new ArrayList<>();

        for(Product product : allProducts) {
            productDTOList.add(getProductDTO(product));
        }

        return productDTOList;
    }


    public void updateProduct(ProductDTO productDTO, Integer productId) throws Exception {
        Optional<Product> optionalProduct = productRepo.findById(productId);

        if(!optionalProduct.isPresent()){
            throw new Exception("Product is not present");
        }

        Product product = optionalProduct.get();

        product.setDescription(productDTO.getDescription());
        product.setImageURL(productDTO.getImageURL());
        product.setPrice(productDTO.getPrice());
        product.setName(productDTO.getName());

        productRepo.save(product);

    }

    public Product findById(Integer productId) throws ProductNotExistException{
        Optional<Product> optionalProduct = productRepo.findById(productId);

        if(optionalProduct.isEmpty()){
            throw new ProductNotExistException("Invalid product id: " + productId);
        }

        return optionalProduct.get();
    }

    public void deleteProduct(Integer productId) {

        Optional<Product> optionalProduct = productRepo.findById(productId);

        if(optionalProduct.isEmpty()){
            throw new CustomException("Invalid product id: " + productId);
        }

        Product product = optionalProduct.get();

        productRepo.delete(product);
    }
}
