package com.example.userservice.Controller;

import com.example.userservice.DTO.LoginDTO;
import com.example.userservice.DTO.UserSignupDTO;
import com.example.userservice.DTO.VerifyTokenDTO;
import com.example.userservice.Exception.SmallPasswordException;
import com.example.userservice.Exception.UserNotFoundException;
import com.example.userservice.Service.UserService;
import com.example.userservice.Model.User;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/UserController")
public class UserController {
    String key = "csc8-23ij-ed90-de90";
    @Autowired
    public UserService userService;

    @PostMapping("/signup")
    @ExceptionHandler(SmallPasswordException.class)
    public User signup(@RequestBody UserSignupDTO userSignupDTO) {
       return userService.signup(userSignupDTO.getUsername(),userSignupDTO.getPassword(),userSignupDTO.getRole());
    }

    @PostMapping("/login")
    @ExceptionHandler(UserNotFoundException.class)
    public String Login(@RequestBody LoginDTO loginDTO) {
        // The token is returned
        return userService.login(loginDTO.getUsername(),loginDTO.getPassword(),key);
    }

    @PostMapping("/generateToken")
    public String generateToken(@RequestBody VerifyTokenDTO verifyTokenDTO) {
        return  null;
    }

    @PostMapping("/verifyToken")
    public Claims verifyToken(@RequestBody VerifyTokenDTO verifyTokenDTO) {
        return userService.verifyToken(verifyTokenDTO.getToken(),key);
    }
}
