package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.common.ApiResponse;
import com.ecommerce.ecommerce.dto.wishlist.WishlistDTO;
import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.service.AuthenticationTokenService;
import com.ecommerce.ecommerce.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    WishlistService wishlistService;

    @Autowired
    AuthenticationTokenService authenticationTokenService;

    @PostMapping("/add/{productId}")
    public ResponseEntity<ApiResponse> addToWishlist(@PathVariable("productId") Integer productId, @RequestParam("Token") String token) {

        authenticationTokenService.authenticate(token);

        User user = authenticationTokenService.getUser(token);

        wishlistService.addToWishlist(productId, user);

        return new ResponseEntity<>(new ApiResponse(true, "successfully added to wishlist"), HttpStatus.CREATED);
    }

    @GetMapping("/list/{token}")
    public ResponseEntity<WishlistDTO> getWishlistItems(@RequestParam("token") String token){

        authenticationTokenService.authenticate(token);

        User user = authenticationTokenService.getUser(token);

        WishlistDTO wishlistDTO = wishlistService.listWishlistItems(user);

        return new ResponseEntity<>(wishlistDTO, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{wishlistItemId}")
    public ResponseEntity<ApiResponse> deleteWishlistItem(@PathVariable("wishlistItemId") Integer wishlistItemId, @RequestParam("Token") String token){

        authenticationTokenService.authenticate(token);

        User user = authenticationTokenService.getUser(token);

        wishlistService.deleteWishlistItem(wishlistItemId, user);

        return new ResponseEntity<>(new ApiResponse(true, "Successfully deleted item from wishlist"), HttpStatus.OK);

    }
}
