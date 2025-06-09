package com.ousl.examinations.repository;

import com.ousl.examinations.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {
    List<Program> findByStatusTrue();
    Optional<Program> findByProgramCode(String programCode);
    boolean existsByProgramCode(String programCode);
}
