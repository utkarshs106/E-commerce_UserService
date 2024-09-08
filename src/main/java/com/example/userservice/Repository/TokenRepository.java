package com.example.userservice.Repository;

import com.example.userservice.Model.Token;
import com.example.userservice.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TokenRepository extends JpaRepository<Token, Integer> {
    Token findByuser(User user);
    Token save(Token token);

    @Override
    void deleteAll();

}

