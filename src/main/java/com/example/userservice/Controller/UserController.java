package com.example.userservice.Controller;

import com.example.userservice.DTO.LoginDTO;
import com.example.userservice.DTO.VerifyTokenDTO;
import com.example.userservice.Service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/UserController")
public class UserController {
    @Autowired
    public UserService userService;

    @PostMapping("/login")
    public String Login(@RequestBody LoginDTO loginDTO) {
       System.out.println("hi");
        return userService.login(loginDTO.getUsername(),loginDTO.getPassword());
    }

    @PostMapping("/verify")
    public String Register(@RequestBody VerifyTokenDTO verifyTokenDTO) {
        return userService.verifyToken(verifyTokenDTO.getToken(),verifyTokenDTO.getPassword());
    }
}
