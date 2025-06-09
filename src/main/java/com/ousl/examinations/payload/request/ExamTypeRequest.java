package com.ousl.examinations.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ExamTypeRequest {
    
    @NotBlank(message = "Exam type name is required")
    @Size(max = 100, message = "Exam type name cannot exceed 100 characters")
    private String examTypeName;

    private String examTypeCode;
    
    @NotNull(message = "Program ID is required")
    private Long programId;
    
    private Boolean status;

    public String getExamTypeName() {
        return examTypeName;
    }

    public void setExamTypeName(String examTypeName) {
        this.examTypeName = examTypeName;
    }

    public String getExamTypeCode() {
        return examTypeCode;
    }

    public void setExamTypeCode(String examTypeCode) {
        this.examTypeCode = examTypeCode;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
