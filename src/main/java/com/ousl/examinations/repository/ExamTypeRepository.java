package com.ousl.examinations.repository;

import com.ousl.examinations.model.ExamType;
import com.ousl.examinations.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExamTypeRepository extends JpaRepository<ExamType, Long> {
    List<ExamType> findByStatusTrue();
    List<ExamType> findByProgramAndStatusTrue(Program program);
    Optional<ExamType> findByExamTypeCode(String examTypeCode);
    boolean existsByExamTypeCode(String examTypeCode);
}
