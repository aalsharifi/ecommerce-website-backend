package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.common.ApiResponse;
import com.ecommerce.ecommerce.dto.ProductDTO;
import com.ecommerce.ecommerce.dto.cart.AddToCartDTO;
import com.ecommerce.ecommerce.dto.cart.CartDTO;
import com.ecommerce.ecommerce.model.Product;
import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.model.Wishlist;
import com.ecommerce.ecommerce.service.AuthenticationTokenService;
import com.ecommerce.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    AuthenticationTokenService authenticationTokenService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDTO addToCartDTO, @RequestParam("Token") String token) {

        authenticationTokenService.authenticate(token);

        User user = authenticationTokenService.getUser(token);

        cartService.addToCart(addToCartDTO, user);

        return new ResponseEntity<>(new ApiResponse(true, "successfully added to cart"), HttpStatus.CREATED);
    }

    @GetMapping("/list/{token}")
    public ResponseEntity<CartDTO> getCartItems(@RequestParam("token") String token){

        authenticationTokenService.authenticate(token);

        User user = authenticationTokenService.getUser(token);

        CartDTO cartDTO = cartService.listCartItems(user);

        return new ResponseEntity<>(cartDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") Integer cartItemId, @RequestParam("Token") String token){

        authenticationTokenService.authenticate(token);

        User user = authenticationTokenService.getUser(token);

        cartService.deleteCartItem(cartItemId, user);

        return new ResponseEntity<>(new ApiResponse(true, "deleted item from cart"), HttpStatus.OK);

    }



}
