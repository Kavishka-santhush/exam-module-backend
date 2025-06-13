package com.ousl.examinations.service.Impl;

import com.ousl.examinations.model.ExamType;
import com.ousl.examinations.model.Program;
import com.ousl.examinations.payload.request.ExamTypeRequest;
import com.ousl.examinations.payload.response.ExamTypeResponse;
import com.ousl.examinations.repository.ExamTypeRepository;
import com.ousl.examinations.repository.ProgramRepository;
import com.ousl.examinations.service.ExamTypeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamTypeServiceImpl implements ExamTypeService {

    @Autowired
    private ExamTypeRepository examTypeRepository;

    @Autowired
    private ProgramRepository programRepository;

    @Override
    public ExamTypeResponse createExamType(ExamTypeRequest examTypeRequest) {

        Program program = programRepository.findById(examTypeRequest.getProgramId())
                .orElseThrow(() -> new EntityNotFoundException("Program not found with id: " + examTypeRequest.getProgramId()));
        

        String examTypeCode = examTypeRequest.getExamTypeCode();
        if (examTypeCode == null || examTypeCode.trim().isEmpty()) {
            examTypeCode = generateExamTypeCode(examTypeRequest.getExamTypeName(), program.getProgramCode());
        }

        if (examTypeRepository.existsByExamTypeCode(examTypeCode)) {
            examTypeCode = examTypeCode + "-" + String.format("%03d", (int)(Math.random() * 1000));
        }

        ExamType examType = new ExamType();
        examType.setExamTypeName(examTypeRequest.getExamTypeName());
        examType.setExamTypeCode(examTypeCode);
        examType.setProgram(program);
        
        if (examTypeRequest.getStatus() != null) {
            examType.setStatus(examTypeRequest.getStatus());
        }

        ExamType savedExamType = examTypeRepository.save(examType);
        
        return mapExamTypeToResponse(savedExamType);
    }
    

    private String generateExamTypeCode(String examTypeName, String programCode) {
        String[] words = examTypeName.split("\\s+");
        StringBuilder codeBuilder = new StringBuilder();

        codeBuilder.append(programCode).append("-");

        for (int i = 0; i < Math.min(words.length, 3); i++) {
            if (!words[i].isEmpty()) {
                codeBuilder.append(words[i].substring(0, 1).toUpperCase());
            }
        }
        
        return codeBuilder.toString();
    }

    @Override
    public ExamTypeResponse updateExamType(Long id, ExamTypeRequest examTypeRequest) {
        ExamType examType = examTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Exam type not found with id: " + id));

        Program program = programRepository.findById(examTypeRequest.getProgramId())
                .orElseThrow(() -> new EntityNotFoundException("Program not found with id: " + examTypeRequest.getProgramId()));

        String examTypeCode = examTypeRequest.getExamTypeCode();
        if (examTypeCode == null || examTypeCode.trim().isEmpty()) {
            if (!examType.getExamTypeName().equals(examTypeRequest.getExamTypeName())) {
                examTypeCode = generateExamTypeCode(examTypeRequest.getExamTypeName(), program.getProgramCode());

                if (examTypeRepository.existsByExamTypeCode(examTypeCode) && 
                        !examTypeCode.equals(examType.getExamTypeCode())) {
                    examTypeCode = examTypeCode + "-" + String.format("%03d", (int)(Math.random() * 1000));
                }
            } else {
                examTypeCode = examType.getExamTypeCode();
            }
        } else {
            if (!examTypeCode.equals(examType.getExamTypeCode()) && 
                    examTypeRepository.existsByExamTypeCode(examTypeCode)) {
                examTypeCode = examTypeCode + "-" + String.format("%03d", (int)(Math.random() * 1000));
            }
        }
        
        examType.setExamTypeName(examTypeRequest.getExamTypeName());
        examType.setExamTypeCode(examTypeCode);
        examType.setProgram(program);
        
        if (examTypeRequest.getStatus() != null) {
            examType.setStatus(examTypeRequest.getStatus());
        }

        ExamType updatedExamType = examTypeRepository.save(examType);
        
        return mapExamTypeToResponse(updatedExamType);
    }

    @Override
    public ExamTypeResponse getExamTypeById(Long id) {
        ExamType examType = examTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Exam type not found with id: " + id));
        
        return mapExamTypeToResponse(examType);
    }

    @Override
    public List<ExamTypeResponse> getAllExamTypes() {
        List<ExamType> examTypes = examTypeRepository.findByStatusTrue();
        
        return examTypes.stream()
                .map(this::mapExamTypeToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExamTypeResponse> getExamTypesByProgramId(Long programId) {
        Program program = programRepository.findById(programId)
                .orElseThrow(() -> new EntityNotFoundException("Program not found with id: " + programId));
        
        List<ExamType> examTypes = examTypeRepository.findByProgramAndStatusTrue(program);
        
        return examTypes.stream()
                .map(this::mapExamTypeToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteExamType(Long id) {
        ExamType examType = examTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Exam type not found with id: " + id));
        
        examType.setStatus(false);
        examTypeRepository.save(examType);
    }
    
    private ExamTypeResponse mapExamTypeToResponse(ExamType examType) {
        ExamTypeResponse response = new ExamTypeResponse();
        response.setId(examType.getId());
        response.setExamTypeName(examType.getExamTypeName());
        response.setExamTypeCode(examType.getExamTypeCode());
        response.setProgramId(examType.getProgram().getId());
        response.setProgramName(examType.getProgram().getProgramName());
        response.setStatus(examType.getStatus());
        response.setCreatedBy(examType.getCreatedBy());
        response.setCreatedDate(examType.getCreatedDate());
        response.setLastModifiedBy(examType.getLastModifiedBy());
        response.setLastModifiedDate(examType.getLastModifiedDate());
        
        return response;
    }
}
