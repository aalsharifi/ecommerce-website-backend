package com.ecommerce.ecommerce.controller;


import com.ecommerce.ecommerce.dto.SignOutResponseDTO;
import com.ecommerce.ecommerce.dto.SignInResponseDTO;
import com.ecommerce.ecommerce.dto.user.SignInDTO;
import com.ecommerce.ecommerce.dto.user.SignUpDTO;
import com.ecommerce.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public SignOutResponseDTO signup(@RequestBody SignUpDTO signUpDTO){
        return userService.signUp(signUpDTO);
    }

    @PostMapping("/signin")
    public SignInResponseDTO signin(@RequestBody SignInDTO signInDTO){
        return userService.signIn(signInDTO);
    }

}
