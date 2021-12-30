package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.ResponseDTO;
import com.ecommerce.ecommerce.dto.user.SignUpDTO;
import com.ecommerce.ecommerce.exceptions.CustomException;
import com.ecommerce.ecommerce.model.AuthenticationToken;
import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    AuthenticationTokenService authenticationTokenService;

    @Transactional
    public ResponseDTO signUp(SignUpDTO signUpDTO) {

        if(Objects.nonNull(userRepo.findByEmail(signUpDTO.getEmail()))) {
            throw new CustomException("User already exists");
        }

        String encryptedPassword = signUpDTO.getPassword();

        try{
            encryptedPassword = hashPassword(signUpDTO.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        User user = new User(signUpDTO.getFirstName(), signUpDTO.getLastName(), signUpDTO.getEmail(), encryptedPassword);

        userRepo.save(user);


        final AuthenticationToken authenticationToken = new AuthenticationToken(user);

        authenticationTokenService.saveToken(authenticationToken);

        ResponseDTO responseDTO = new ResponseDTO("success", "User created successfully");
        return responseDTO;
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {

        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(password.getBytes());
        byte [] digest = messageDigest.digest();
        String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();

        return hash;

    }
}
