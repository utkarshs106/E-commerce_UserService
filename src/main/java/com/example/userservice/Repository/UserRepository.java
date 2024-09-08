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
    User save(User user);

    @Query(value = "Select id from roles where role_type = :role_type", nativeQuery = true)
    Integer findByRoleType(@Param("role_type") String role_type);

    @Override
    void deleteAll();

    User findByuserName(String username);


}
