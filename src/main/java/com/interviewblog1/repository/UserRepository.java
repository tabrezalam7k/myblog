package com.interviewblog1.repository;

import com.interviewblog1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameOrEmail(String username, String email);
    Optional<User>findByEmail(String email);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
