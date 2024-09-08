package com.example.userservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.userservice.Model.Roles;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Roles, Long> {
    Roles findByRoleType(String name);
    Roles save(Roles roles);
    Boolean existsByRoleType(String name);

    @Override
    void deleteAll();
}
