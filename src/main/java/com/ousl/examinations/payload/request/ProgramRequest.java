package com.ousl.examinations.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProgramRequest {
    
    @NotBlank(message = "Program name is required")
    @Size(max = 100, message = "Program name cannot exceed 100 characters")
    private String programName;
    
    @NotBlank(message = "Program code is required")
    @Size(max = 20, message = "Program code cannot exceed 20 characters")
    private String programCode;
    
    private Boolean status;

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
