package com.example.userservice.Service;

import com.example.userservice.DTO.UserSignupDTO;
import com.example.userservice.Repository.RoleRepository;
import com.example.userservice.Repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import com.example.userservice.Model.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Transactional
    public User signup(String userName, String password, String roleType) {
        // Check if the role exists
        Roles role = roleRepository.findByRoleType(roleType);

        if (role == null) {
            // Create new role if not exists
            role = new Roles();
            role.setRoleType(roleType);
            roleRepository.save(role);
        }

        // Create new user
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setRole(role);

        return userRepository.save(user);
    }

    public String login(String username, String password){
       return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS512, password)
                .compact();
    }

    public String verifyToken(String token,String password){
        return Jwts.parser()
                .setSigningKey(password)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
