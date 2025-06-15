package com.ousl.examinations.model;

import jakarta.persistence.*;

@Entity
@Table(name = "final_exam_criteria")
public class FinalExamCriteria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "min_marks", nullable = false)
    private int minMarks;

    @Column(name = "max_mark", nullable = false)
    private int maxMark;

    @Column(name = "grade", length = 10, nullable = false)
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
