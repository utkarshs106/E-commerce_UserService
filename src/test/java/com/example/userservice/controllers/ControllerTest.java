package com.example.userservice.controllers;

import com.example.userservice.Controller.UserController;
import com.example.userservice.DTO.LoginDTO;
import com.example.userservice.DTO.UserSignupDTO;
import com.example.userservice.DTO.VerifyTokenDTO;
import com.example.userservice.Repository.RoleRepository;
import com.example.userservice.Repository.TokenRepository;
import com.example.userservice.Repository.UserRepository;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class ControllerTest {
    @Autowired
    private UserController userController;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Test
    void Positive_signup_login_tokenvalidation() {

        tokenRepository.deleteAll();
        userRepository.deleteAll();
        roleRepository.deleteAll();

        UserSignupDTO signupDTO = new UserSignupDTO();
        signupDTO.setUsername("Utkarsh");
        signupDTO.setPassword("12345675r4342");
        signupDTO.setRole("Master");

        userController.signup(signupDTO);

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("Utkarsh");
        loginDTO.setPassword("12345675r4342");

      String s1 =  userController.Login(loginDTO);

        VerifyTokenDTO verifyTokenDTO = new VerifyTokenDTO();
        verifyTokenDTO.setToken(s1);

     Claims c1 = userController.verifyToken(verifyTokenDTO);
     System.out.println(c1);

     assertEquals(c1.get("role"), "Master");
     assertEquals(c1.get("name"), "Utkarsh");


    }

    @Test
    void Positive_signup_login(){
        tokenRepository.deleteAll();
        userRepository.deleteAll();
        roleRepository.deleteAll();

        UserSignupDTO signupDTO = new UserSignupDTO();
        signupDTO.setUsername("Shweta");
        signupDTO.setPassword("1qw2e34rt5");
        signupDTO.setRole("Child");
        userController.signup(signupDTO);
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("Shweta");
        loginDTO.setPassword("1qw2e34rt5");
        String s1 =  userController.Login(loginDTO);

        if(s1.isEmpty()){
            assertFalse(true);
        }else{
            assertFalse(false);
        }
    }

    @Test
    void Negative_smallPassword(){
        tokenRepository.deleteAll();
        userRepository.deleteAll();
        roleRepository.deleteAll();

        UserSignupDTO signupDTO = new UserSignupDTO();
        signupDTO.setUsername("kusum");
        signupDTO.setPassword("1234");
        signupDTO.setRole("Child");

        try {
            userController.signup(signupDTO);
        }catch (Exception e){
            System.out.println(e);
        }


        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("kusum");
        loginDTO.setPassword("1234");
        String s1 = null;
        try {
            s1 =  userController.Login(loginDTO);
        }catch (Exception e){
            System.out.println(e);
        }

        if(s1==null){
            assertFalse(false);
        }else{
            assertFalse(true);
        }


    }
}