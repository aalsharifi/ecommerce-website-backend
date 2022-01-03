package com.ecommerce.ecommerce.dto.wishlist;

import com.ecommerce.ecommerce.model.Product;
import com.ecommerce.ecommerce.model.Wishlist;

public class WishlistItemsDTO {

    private Integer id;
    private Product product;

    public WishlistItemsDTO(Wishlist wishlist) {
        this.id = wishlist.getId();
        this.setProduct(wishlist.getProduct());
    }

    public WishlistItemsDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
