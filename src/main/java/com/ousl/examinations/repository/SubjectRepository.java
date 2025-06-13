package com.ousl.examinations.repository;

import com.ousl.examinations.model.Program;
import com.ousl.examinations.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findByProgram(Program program);
}