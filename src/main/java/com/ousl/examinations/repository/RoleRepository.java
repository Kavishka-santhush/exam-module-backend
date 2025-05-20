package com.ousl.examinations.repository;

import com.ousl.examinations.model.ERole;
import com.ousl.examinations.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(ERole name);
}
