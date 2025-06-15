package com.ousl.examinations.payload;

import java.util.List;

public class GradeCriteriaDTO {

    private String grade;
    private List<FinalExamCriteriaDTO> criteriaList;

    // Getters and Setters

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public List<FinalExamCriteriaDTO> getCriteriaList() {
        return criteriaList;
    }

    public void setCriteriaList(List<FinalExamCriteriaDTO> criteriaList) {
        this.criteriaList = criteriaList;
    }
}
