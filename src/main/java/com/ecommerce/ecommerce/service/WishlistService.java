package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.model.Wishlist;
import com.ecommerce.ecommerce.repository.WishlistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {

    @Autowired
    WishlistRepo wishlistRepo;

    public void createWishlist(Wishlist wishlist) {
        wishlistRepo.save(wishlist);
    }
}
