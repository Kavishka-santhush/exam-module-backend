package com.ousl.examinations.service.Impl;

import com.ousl.examinations.model.FinalExamCriteria;
import com.ousl.examinations.repository.FinalExamCriteriaRepository;
import com.ousl.examinations.service.FinalExamCriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FinalExamCriteriaServiceImpl implements FinalExamCriteriaService {

    @Autowired
    private FinalExamCriteriaRepository finalExamCriteriaRepository;

    @Override
    public List<FinalExamCriteria> getAllFinalExamCriteria() {
        return finalExamCriteriaRepository.findAll();
    }

    @Override
    public FinalExamCriteria getFinalExamCriteriaById(Long id) {
        Optional<FinalExamCriteria> optionalFinalExamCriteria = finalExamCriteriaRepository.findById(id);
        return optionalFinalExamCriteria.orElse(null);
    }

    @Override
    public FinalExamCriteria createFinalExamCriteria(FinalExamCriteria finalExamCriteria) {
        return finalExamCriteriaRepository.save(finalExamCriteria);
    }

    @Override
    public FinalExamCriteria updateFinalExamCriteria(Long id, FinalExamCriteria finalExamCriteriaDetails) {
        FinalExamCriteria finalExamCriteria = finalExamCriteriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FinalExamCriteria not found with id: " + id));

        finalExamCriteria.setMinMarks(finalExamCriteriaDetails.getMinMarks());
        finalExamCriteria.setMaxMark(finalExamCriteriaDetails.getMaxMark());
        finalExamCriteria.setGrade(finalExamCriteriaDetails.getGrade());

        return finalExamCriteriaRepository.save(finalExamCriteria);
    }

    @Override
    public void deleteFinalExamCriteria(Long id) {
        finalExamCriteriaRepository.deleteById(id);
    }

    @Override
    public List<String> getDistinctGrades() {
        return finalExamCriteriaRepository.findDistinctGrades();
    }
}
