package com.example.userservice.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class VerifyTokenDTO {
    String token;
    String password;
}
