package com.ousl.examinations.service;

import com.ousl.examinations.model.FinalExamCriteria;
import com.ousl.examinations.model.Program;
import com.ousl.examinations.model.ScExamProgMarksCriteria;
import com.ousl.examinations.payload.FinalExamCriteriaDTO;
import com.ousl.examinations.payload.GradeCriteriaDTO;
import com.ousl.examinations.payload.SaveCriteriaBindingDTO;
import com.ousl.examinations.repository.FinalExamCriteriaRepository;
import com.ousl.examinations.repository.ProgramRepository;
import com.ousl.examinations.repository.ScExamProgMarksCriteriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExamCriteriaService {

    private static final List<String> GRADE_ORDER = Arrays.asList(
            "A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "F"
    );

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private FinalExamCriteriaRepository finalExamCriteriaRepository;

    @Autowired
    private ScExamProgMarksCriteriaRepository scExamProgMarksCriteriaRepository;

    public List<Program> getAllPrograms() {
        return programRepository.findAll();
    }

    public List<GradeCriteriaDTO> getGradesAndCriteria() {
        List<String> distinctGrades = finalExamCriteriaRepository.findDistinctGrades();

        // Sort grades according to the predefined academic order
        distinctGrades.sort((g1, g2) -> {
            int index1 = GRADE_ORDER.indexOf(g1);
            int index2 = GRADE_ORDER.indexOf(g2);

            if (index1 == -1 && index2 == -1) {
                return g1.compareTo(g2); // Both grades not in predefined list, sort alphabetically
            }
            if (index1 == -1) {
                return 1; // g1 is not in list, comes after g2
            }
            if (index2 == -1) {
                return -1; // g2 is not in list, comes after g1
            }
            return Integer.compare(index1, index2); // Both in list, sort by predefined order
        });

        return distinctGrades.stream().map(grade -> {
            GradeCriteriaDTO dto = new GradeCriteriaDTO();
            dto.setGrade(grade);
            List<FinalExamCriteria> criteria = finalExamCriteriaRepository.findByGrade(grade);
            dto.setCriteriaList(criteria.stream().map(this::convertToDto).collect(Collectors.toList()));
            return dto;
        }).collect(Collectors.toList());
    }

    public List<ScExamProgMarksCriteria> getSavedCriteria(Long programId) {
        return scExamProgMarksCriteriaRepository.findByProgramId(programId);
    }

    public void saveCriteriaBindings(SaveCriteriaBindingDTO dto) {
        Long programId = dto.getProgramId();
        Program program = programRepository.findById(programId).orElseThrow(() -> new RuntimeException("Program not found"));

        List<ScExamProgMarksCriteria> existingBindings = scExamProgMarksCriteriaRepository.findByProgramId(programId);
        Map<String, ScExamProgMarksCriteria> existingBindingsMap = existingBindings.stream()
                .collect(Collectors.toMap(b -> b.getFinalExamCriteria().getGrade(), b -> b));

        for (Map.Entry<String, Long> entry : dto.getBindings().entrySet()) {
            String grade = entry.getKey();
            Long criteriaId = entry.getValue();
            FinalExamCriteria criteria = finalExamCriteriaRepository.findById(criteriaId).orElseThrow(() -> new RuntimeException("Criteria not found"));

            ScExamProgMarksCriteria binding = existingBindingsMap.get(grade);
            if (binding == null) {
                binding = new ScExamProgMarksCriteria();
                binding.setProgram(program);
            }
            binding.setFinalExamCriteria(criteria);
            binding.setMarks(criteria.getMinMarks());
            scExamProgMarksCriteriaRepository.save(binding);
        }
    }

    private FinalExamCriteriaDTO convertToDto(FinalExamCriteria criteria) {
        FinalExamCriteriaDTO dto = new FinalExamCriteriaDTO();
        dto.setId(criteria.getId());
        dto.setMinMarks(criteria.getMinMarks());
        dto.setMaxMark(criteria.getMaxMark());
        dto.setGrade(criteria.getGrade());
        return dto;
    }
}
