package com.example.userservice.Service;

import com.example.userservice.DTO.UserSignupDTO;
import com.example.userservice.Repository.RoleRepository;
import com.example.userservice.Repository.TokenRepository;
import com.example.userservice.Repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import com.example.userservice.Model.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;
    @Autowired
    TokenRepository tokenRepository;

    UserService(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

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

        String s1 = passwordEncoder.encode(password);

        User user = new User();
        user.setUserName(userName);
        user.setPassword(s1);
        user.setRole(role);

        return userRepository.save(user);

    }

    public String login(String username, String inputPassword,String key){
        User u1 = userRepository.findByuserName(username);
        System.out.println(u1.getPassword());
        System.out.println(inputPassword);
        if(u1 != null && passwordEncoder.matches(inputPassword, u1.getPassword())){

            //If Token is present for user return token
            Token t1 = tokenRepository.findByuser(u1);
            if(t1 != null){
                return t1.getToken();
            }else{
                //Token is not present
                String tokenString = Jwts.builder()
                        .setSubject(username)
                        .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                        .signWith(SignatureAlgorithm.HS512, key)
                        .compact();

                //creating new Token object and adding vaues
                Token token = new Token();
                token.setToken(tokenString);
                token.setUser(u1);
                tokenRepository.save(token);
                return tokenRepository.findByuser(u1).getToken();
            }

        }
        else{
            return "Invalid username or password";
        }

    }

    public String verifyToken(String token,String password){
        return Jwts.parser()
                .setSigningKey(password)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
