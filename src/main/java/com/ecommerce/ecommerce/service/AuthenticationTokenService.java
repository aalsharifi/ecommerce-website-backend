package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.exceptions.AuthenticationFailedException;
import com.ecommerce.ecommerce.model.AuthenticationToken;
import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.repository.AuthenticationTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationTokenService {

    @Autowired
    AuthenticationTokenRepo authenticationTokenRepo;

    public void saveToken(AuthenticationToken authenticationToken) {
        authenticationTokenRepo.save(authenticationToken);
    }

    public AuthenticationToken getToken(User user) {
        return authenticationTokenRepo.findByUser(user);
    }

    public User getUser(String token){
        final AuthenticationToken authenticationToken = authenticationTokenRepo.findByToken(token);

        if(Objects.isNull(authenticationToken)){
            return null;
        }

        return authenticationToken.getUser();
    }

    public void authenticate(String token){
        if(Objects.isNull(token)) {
            throw new AuthenticationFailedException("Token is not found");
        }

        if(Objects.isNull(getUser(token))){
            throw new AuthenticationFailedException("Invalid token");
        }

    }
}
