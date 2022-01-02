package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.cart.AddToCartDTO;
import com.ecommerce.ecommerce.dto.cart.CartDTO;
import com.ecommerce.ecommerce.dto.cart.CartItemsDTO;
import com.ecommerce.ecommerce.exceptions.CustomException;
import com.ecommerce.ecommerce.model.Cart;
import com.ecommerce.ecommerce.model.Product;
import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.repository.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    CartRepo cartRepo;

    @Autowired
    ProductService productService;

    public void addToCart(AddToCartDTO addToCartDTO, User user) {

        Product product = productService.findById(addToCartDTO.getProductId());

        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setUser(user);
        cart.setQuantity(addToCartDTO.getQuantity());
        cart.setCreatedDate(new Date());

        cartRepo.save(cart);
    }

    public CartDTO listCartItems(User user) {
        List<Cart> cartList = cartRepo.findAllByUserOrderByCreatedDateDesc(user);
        List<CartItemsDTO> cartItemsDTOList = new ArrayList<>();
        double totalCost = 0;

        for(Cart cart : cartList){
            CartItemsDTO cartItemsDTO = new CartItemsDTO(cart);
            totalCost += cartItemsDTO.getQuantity() * cart.getProduct().getPrice();
            cartItemsDTOList.add(cartItemsDTO);
        }

        DecimalFormat formatter = new DecimalFormat("#0.00");

        CartDTO cartDTO = new CartDTO();
        cartDTO.setTotalCost(Double.parseDouble(formatter.format(totalCost)));
        cartDTO.setCartItemsDTOList(cartItemsDTOList);

        return cartDTO;
    }

    public void deleteCartItem(Integer cartItemId, User user) {

        Optional<Cart> optionalCart = cartRepo.findById(cartItemId);

        if(optionalCart.isEmpty()){
            throw new CustomException("Invalid cart item id: " + cartItemId);
        }

        Cart cart = optionalCart.get();

        if(cart.getUser() != user){
            throw new CustomException("Cart item does not belong to user: " + cartItemId);
        }

        cartRepo.delete(cart);
    }


    public void updateCartItem(Integer quantity, Integer cartItemId, User user) {

        Optional<Cart> optionalCart = cartRepo.findById(cartItemId);

        if(optionalCart.isEmpty()){
            throw new CustomException("Invalid cart item id: " + cartItemId);
        }

        Cart cart = optionalCart.get();

        if(cart.getUser() != user){
            throw new CustomException("Cart item does not belong to user: " + cartItemId);
        }

        if(quantity < 0){
            throw new CustomException("Quantity " + quantity + " is less than 0");
        }

        cart.setQuantity(quantity);
        cart.setCreatedDate(new Date());
        cartRepo.save(cart);

    }
}
