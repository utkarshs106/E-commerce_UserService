package com.example.userservice.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignupDTO {
    private String username;
    private String password;
    private String role;
}
