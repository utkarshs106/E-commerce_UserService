package com.example.userservice.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int UserID;
    @Column(unique = true, nullable = false)
    String userName;
    String password;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "role_id", nullable = false)
    Roles Role;
}