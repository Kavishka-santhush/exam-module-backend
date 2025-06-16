package com.ousl.examinations.service;

import com.ousl.examinations.model.FinalExamCriteria;

import java.util.List;

public interface FinalExamCriteriaService {
    List<FinalExamCriteria> getAllFinalExamCriteria();
    FinalExamCriteria getFinalExamCriteriaById(Long id);
    FinalExamCriteria createFinalExamCriteria(FinalExamCriteria finalExamCriteria);
    FinalExamCriteria updateFinalExamCriteria(Long id, FinalExamCriteria finalExamCriteria);
    void deleteFinalExamCriteria(Long id);
    List<String> getDistinctGrades();
}
