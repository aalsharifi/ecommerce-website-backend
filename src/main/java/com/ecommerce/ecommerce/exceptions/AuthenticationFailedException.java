package com.ecommerce.ecommerce.exceptions;

public class AuthenticationFailedException extends IllegalArgumentException{

    public AuthenticationFailedException(String msg){
        super(msg);
    }

}
