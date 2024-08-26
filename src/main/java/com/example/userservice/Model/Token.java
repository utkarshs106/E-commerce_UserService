package com.example.userservice.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String token;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user", nullable = false)
    User user;
}
