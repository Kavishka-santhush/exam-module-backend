package com.ousl.examinations.payload;

import java.util.Map;

public class SaveCriteriaBindingDTO {

    private Long programId;
    private Map<String, Long> bindings; // Map of grade to final_exam_criteria_id

    // Getters and Setters

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public Map<String, Long> getBindings() {
        return bindings;
    }

    public void setBindings(Map<String, Long> bindings) {
        this.bindings = bindings;
    }
}
