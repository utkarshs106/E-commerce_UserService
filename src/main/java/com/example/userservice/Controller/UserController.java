package com.example.userservice.Controller;

import com.example.userservice.DTO.LoginDTO;
import com.example.userservice.DTO.UserSignupDTO;
import com.example.userservice.DTO.VerifyTokenDTO;
import com.example.userservice.Service.UserService;
import com.example.userservice.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/UserController")
public class UserController {
    String key = "csc8-23ij-ed90-de90";
    @Autowired
    public UserService userService;

    @PostMapping("/signup")
    public User signup(@RequestBody UserSignupDTO userSignupDTO) {
       return userService.signup(userSignupDTO.getUsername(),userSignupDTO.getPassword(),userSignupDTO.getRole());
    }

    @PostMapping("/login")
    public String Login(@RequestBody LoginDTO loginDTO) {
       System.out.println("hi");
        return userService.login(loginDTO.getUsername(),key);
    }

    @PostMapping("/generateToken")
    public String generateToken(@RequestBody VerifyTokenDTO verifyTokenDTO) {
        return  null;
    }

    @PostMapping("/verifyToken")
    public String Register(@RequestBody VerifyTokenDTO verifyTokenDTO) {
        return userService.verifyToken(verifyTokenDTO.getToken(),key);
    }
}
