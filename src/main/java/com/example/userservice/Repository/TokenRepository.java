package com.example.userservice.Repository;

import com.example.userservice.Model.Token;
import com.example.userservice.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Integer> {
    Token findByuser(User user);
    Token save(Token token);
}
