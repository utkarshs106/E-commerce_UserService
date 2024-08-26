package com.example.userservice.Repository;

import com.example.userservice.Model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Query(value = "INSERT INTO User (user_name, password, role_id) VALUES (:userName, :password, :role_id)", nativeQuery = true)
    void save(@Param("userName") String userName, @Param("password") String password, @Param("role_id") int role_id);

    User save(User user);

    @Query(value = "Select id from roles where role_type = :role_type", nativeQuery = true)
    Integer findByRoleType(@Param("role_type") String role_type);

}
