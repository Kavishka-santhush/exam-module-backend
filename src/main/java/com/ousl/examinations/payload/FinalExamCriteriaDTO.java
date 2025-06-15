package com.ousl.examinations.payload;

public class FinalExamCriteriaDTO {

    private Long id;
    private int minMarks;
    private int maxMark;
    private String grade;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMinMarks() {
        return minMarks;
    }

    public void setMinMarks(int minMarks) {
        this.minMarks = minMarks;
    }

    public int getMaxMark() {
        return maxMark;
    }

    public void setMaxMark(int maxMark) {
        this.maxMark = maxMark;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
