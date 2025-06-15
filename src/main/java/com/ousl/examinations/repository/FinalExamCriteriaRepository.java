package com.ousl.examinations.repository;

import com.ousl.examinations.model.FinalExamCriteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinalExamCriteriaRepository extends JpaRepository<FinalExamCriteria, Long> {

    @Query("SELECT DISTINCT f.grade FROM FinalExamCriteria f")
    List<String> findDistinctGrades();

    List<FinalExamCriteria> findByGrade(String grade);
}
