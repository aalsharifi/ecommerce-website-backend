package com.ecommerce.ecommerce.dto.cart;

import java.util.List;

public class CartDTO {
    private List<CartItemsDTO> cartItemsDTOList;
    private double totalCost;

    public CartDTO() {
    }

    public List<CartItemsDTO> getCartItemsDTOList() {
        return cartItemsDTOList;
    }

    public void setCartItemsDTOList(List<CartItemsDTO> cartItemsDTOList) {
        this.cartItemsDTOList = cartItemsDTOList;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
