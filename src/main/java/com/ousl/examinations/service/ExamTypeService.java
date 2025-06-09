package com.ousl.examinations.service;

import com.ousl.examinations.payload.request.ExamTypeRequest;
import com.ousl.examinations.payload.response.ExamTypeResponse;

import java.util.List;

public interface ExamTypeService {
    ExamTypeResponse createExamType(ExamTypeRequest examTypeRequest);
    ExamTypeResponse updateExamType(Long id, ExamTypeRequest examTypeRequest);
    ExamTypeResponse getExamTypeById(Long id);
    List<ExamTypeResponse> getAllExamTypes();
    List<ExamTypeResponse> getExamTypesByProgramId(Long programId);
    void deleteExamType(Long id);
}
