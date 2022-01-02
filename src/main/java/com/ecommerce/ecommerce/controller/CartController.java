package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.common.ApiResponse;
import com.ecommerce.ecommerce.dto.cart.AddToCartDTO;
import com.ecommerce.ecommerce.dto.cart.CartDTO;
import com.ecommerce.ecommerce.dto.cart.CartItemsDTO;
import com.ecommerce.ecommerce.exceptions.AuthenticationFailedException;
import com.ecommerce.ecommerce.exceptions.ProductNotExistException;
import com.ecommerce.ecommerce.model.Product;
import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.service.AuthenticationTokenService;
import com.ecommerce.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/update/{cartItemId}")
    public ResponseEntity<ApiResponse> updateCartItem(@PathVariable("cartItemId") Integer cartItemId,
                                                      @RequestParam("token") String token, Integer quantity){

        authenticationTokenService.authenticate(token);

        User user = authenticationTokenService.getUser(token);

        cartService.updateCartItem(quantity, cartItemId, user);

        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Cart has been updated"), HttpStatus.OK);
    }



}
