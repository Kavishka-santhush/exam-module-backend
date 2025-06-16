package com.ousl.examinations.service.Impl;

import com.ousl.examinations.dto.MarksCriteriaDTO;
import com.ousl.examinations.model.Component;
import com.ousl.examinations.model.MarksCriteria;
import com.ousl.examinations.model.Program;
import com.ousl.examinations.repository.ComponentRepository;
import com.ousl.examinations.repository.MarksCriteriaRepository;
import com.ousl.examinations.repository.ProgramRepository;
import com.ousl.examinations.service.MarksCriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarksCriteriaServiceImpl implements MarksCriteriaService {

    @Autowired
    private ComponentRepository componentRepository;

    @Autowired
    private MarksCriteriaRepository marksCriteriaRepository;

    @Autowired
    private ProgramRepository programRepository;

    @Override
    public List<Component> getAllComponents() {
        return componentRepository.findAll();
    }

    @Override
    public List<MarksCriteriaDTO> getMarksCriteriaByProgram(Long programId) {
        return marksCriteriaRepository.findByProgramId(programId).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void saveMarksCriteria(List<MarksCriteriaDTO> marksCriteriaDTOList) {
        for (MarksCriteriaDTO dto : marksCriteriaDTOList) {
            MarksCriteria criteria = (dto.getId() != null)
                    ? marksCriteriaRepository.findById(dto.getId()).orElse(new MarksCriteria())
                    : new MarksCriteria();

            Program program = programRepository.findById(dto.getProgramId())
                    .orElseThrow(() -> new RuntimeException("Program not found with id: " + dto.getProgramId()));

            // Find component by name, or create a new one if it doesn't exist
            Component component = componentRepository.findByComponentName(dto.getComponentName())
                    .orElseGet(() -> {
                        Component newComponent = new Component();
                        newComponent.setComponentName(dto.getComponentName());
                        return componentRepository.save(newComponent);
                    });

            criteria.setProgram(program);
            criteria.setComponent(component);
            criteria.setMinMark(dto.getMinMark());
            criteria.setPercentage(dto.getPercentage());
            marksCriteriaRepository.save(criteria);
        }
    }

    @Override
    public Component addNewComponent(String componentName) {
        Component component = new Component();
        component.setComponentName(componentName);
        return componentRepository.save(component);
    }

    private MarksCriteriaDTO convertToDTO(MarksCriteria marksCriteria) {
        MarksCriteriaDTO dto = new MarksCriteriaDTO();
        dto.setId(marksCriteria.getId());
        dto.setProgramId(marksCriteria.getProgram().getId());
        dto.setComponentName(marksCriteria.getComponent().getComponentName());
        dto.setMinMark(marksCriteria.getMinMark());
        dto.setPercentage(marksCriteria.getPercentage());
        return dto;
    }
}
