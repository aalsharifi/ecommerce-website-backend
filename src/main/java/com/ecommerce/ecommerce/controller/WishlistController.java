package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.common.ApiResponse;
import com.ecommerce.ecommerce.dto.ProductDTO;
import com.ecommerce.ecommerce.model.Product;
import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.model.Wishlist;
import com.ecommerce.ecommerce.service.AuthenticationTokenService;
import com.ecommerce.ecommerce.service.ProductService;
import com.ecommerce.ecommerce.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    WishlistService wishlistService;

    @Autowired
    AuthenticationTokenService authenticationTokenService;

    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToWishlist(@RequestBody Product product, @RequestParam("Token") String token) {

        authenticationTokenService.authenticate(token);

        User user = authenticationTokenService.getUser(token);

        Wishlist wishlist = new Wishlist(user, product);

        wishlistService.createWishlist(wishlist);

        return new ResponseEntity<>(new ApiResponse(true, "successfully added to wishlist"), HttpStatus.CREATED);
    }

    @GetMapping("/list/{token}")
    public ResponseEntity<List<ProductDTO>> getWishlist(@PathVariable("token") String token){

        authenticationTokenService.authenticate(token);

        User user = authenticationTokenService.getUser(token);

        List<ProductDTO> productDTOList =  wishlistService.getWishlistForUser(user);

        return new ResponseEntity<>(productDTOList, HttpStatus.OK);
    }
}
