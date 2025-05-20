package com.ousl.examinations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ousl.examinations.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
