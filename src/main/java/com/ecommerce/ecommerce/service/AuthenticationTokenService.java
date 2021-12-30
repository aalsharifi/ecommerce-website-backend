package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.model.AuthenticationToken;
import com.ecommerce.ecommerce.repository.AuthenticationTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationTokenService {

    @Autowired
    AuthenticationTokenRepo authenticationTokenRepo;

    public void saveToken(AuthenticationToken authenticationToken) {
        authenticationTokenRepo.save(authenticationToken);
    }
}
