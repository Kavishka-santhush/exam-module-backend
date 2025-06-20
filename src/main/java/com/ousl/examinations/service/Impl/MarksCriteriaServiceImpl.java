package com.ousl.examinations.service.Impl;

import com.ousl.examinations.dto.MarksCriteriaDTO;
import com.ousl.examinations.model.Component;
import com.ousl.examinations.model.MarksCriteria;
import com.ousl.examinations.model.Program;
import com.ousl.examinations.model.User;
import com.ousl.examinations.repository.ComponentRepository;
import com.ousl.examinations.repository.MarksCriteriaRepository;
import com.ousl.examinations.repository.ProgramRepository;
import com.ousl.examinations.repository.UserRepository;
import com.ousl.examinations.service.MarksCriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    
    @Autowired
    private UserRepository userRepository;

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
        // Get current user from security context
        User currentUser = getCurrentUser();
        
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
                        newComponent.setUser(currentUser);
                        newComponent.setCreatedAt(LocalDateTime.now());
                        return componentRepository.save(newComponent);
                    });

            criteria.setProgram(program);
            criteria.setComponent(component);
            criteria.setMinMark(dto.getMinMark());
            criteria.setPercentage(dto.getPercentage());
            
            // Set user and created_at for new records
            if (criteria.getId() == null) {
                criteria.setUser(currentUser);
                criteria.setCreatedAt(LocalDateTime.now());
            }
            
            marksCriteriaRepository.save(criteria);
        }
    }

    @Override
    public Component addNewComponent(String componentName) {
        Component component = new Component();
        component.setComponentName(componentName);
        
        // Set user and created_at
        User currentUser = getCurrentUser();
        component.setUser(currentUser);
        component.setCreatedAt(LocalDateTime.now());
        
        return componentRepository.save(component);
    }
    
    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            return userRepository.findByUsername(username);
        }
        return null;
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
