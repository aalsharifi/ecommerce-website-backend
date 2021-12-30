package com.ecommerce.ecommerce.repository;


import com.ecommerce.ecommerce.model.Category;
import com.ecommerce.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
}

