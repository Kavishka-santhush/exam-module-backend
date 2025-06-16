package com.ousl.examinations.service;

import com.ousl.examinations.dto.MarksCriteriaDTO;
import com.ousl.examinations.model.Component;
import com.ousl.examinations.model.Program;

import java.util.List;

public interface MarksCriteriaService {

    List<Component> getAllComponents();

    List<MarksCriteriaDTO> getMarksCriteriaByProgram(Long programId);

    void saveMarksCriteria(List<MarksCriteriaDTO> marksCriteriaDTOList);

    Component addNewComponent(String componentName);
}
