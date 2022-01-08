package com.ecommerce.ecommerce.dto.wishlist;

import com.ecommerce.ecommerce.dto.cart.CartItemsDTO;

import java.util.List;

public class WishlistDTO {
    private List<WishlistItemsDTO> wishlistItemsDTOS;

    public WishlistDTO() {
    }

    public List<WishlistItemsDTO> getWishlistItemsDTOS() {
        return wishlistItemsDTOS;
    }

    public void setWishlistItemsDTOS(List<WishlistItemsDTO> wishlistItemsDTOS) {
        this.wishlistItemsDTOS = wishlistItemsDTOS;
    }
}
