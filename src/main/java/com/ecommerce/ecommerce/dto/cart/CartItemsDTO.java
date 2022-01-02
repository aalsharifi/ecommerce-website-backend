package com.ecommerce.ecommerce.dto.cart;

import com.ecommerce.ecommerce.model.Cart;
import com.ecommerce.ecommerce.model.Product;

public class CartItemsDTO {
    private Integer id;
    private Integer quantity;
    private Product product;

    public CartItemsDTO(Cart cart) {
        this.id = cart.getId();
        this.quantity = cart.getQuantity();
        this.setProduct(cart.getProduct());
    }

    public CartItemsDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


}
