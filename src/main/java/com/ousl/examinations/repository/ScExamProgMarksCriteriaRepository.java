package com.ousl.examinations.repository;

import com.ousl.examinations.model.ScExamProgMarksCriteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScExamProgMarksCriteriaRepository extends JpaRepository<ScExamProgMarksCriteria, Long> {

    List<ScExamProgMarksCriteria> findByProgramId(Long programId);
}
