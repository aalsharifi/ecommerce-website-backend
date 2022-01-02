package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.ProductDTO;
import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.model.Wishlist;
import com.ecommerce.ecommerce.repository.WishlistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishlistService {

    @Autowired
    WishlistRepo wishlistRepo;

    @Autowired
    ProductService productService;

    public void createWishlist(Wishlist wishlist) {
        wishlistRepo.save(wishlist);
    }

    public List<ProductDTO> getWishlistForUser(User user) {
        final List<Wishlist> list = wishlistRepo.findAllByUserOrderByCreatedDateDesc(user);
        List<ProductDTO> productDTOList = new ArrayList<>();

        for(Wishlist wish : list) {
            productDTOList.add(productService.getProductDTO(wish.getProduct()));
        }

        return productDTOList;
    }
}
