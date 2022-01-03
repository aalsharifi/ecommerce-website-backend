package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.wishlist.WishlistDTO;
import com.ecommerce.ecommerce.dto.wishlist.WishlistItemsDTO;
import com.ecommerce.ecommerce.exceptions.CustomException;
import com.ecommerce.ecommerce.model.Product;
import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.model.Wishlist;
import com.ecommerce.ecommerce.repository.WishlistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WishlistService {

    @Autowired
    WishlistRepo wishlistRepo;

    @Autowired
    ProductService productService;

    public void deleteWishlistItem(Integer wishlistItemId, User user) {

        Optional<Wishlist> optionalWishlist = wishlistRepo.findById(wishlistItemId);

        if(optionalWishlist.isEmpty()){
            throw new CustomException("Invalid wishlist item id: " + wishlistItemId);
        }

        Wishlist wishlist = optionalWishlist.get();

        if(wishlist.getUser() != user){
            throw new CustomException("wishlist item does not belong to user: " + wishlistItemId);
        }

        wishlistRepo.delete(wishlist);
    }


    public WishlistDTO listWishlistItems(User user) {

        List<Wishlist> wishlists = wishlistRepo.findAllByUserOrderByCreatedDateDesc(user);
        List<WishlistItemsDTO> wishlistItemsDTOList = new ArrayList<>();

        for(Wishlist wishlist : wishlists){
            WishlistItemsDTO wishlistItemsDTO = new WishlistItemsDTO(wishlist);
            wishlistItemsDTOList.add(wishlistItemsDTO);
        }

        WishlistDTO wishlistDTO = new WishlistDTO();
        wishlistDTO.setWishlistItemsDTOS(wishlistItemsDTOList);

        return wishlistDTO;
    }


    public void addToWishlist(Integer productId, User user) {

        Product product = productService.findById(productId);

        Wishlist wishlist = new Wishlist();

        wishlist.setProduct(product);
        wishlist.setUser(user);
        wishlist.setCreatedDate(new Date());

        wishlistRepo.save(wishlist);

    }
}
