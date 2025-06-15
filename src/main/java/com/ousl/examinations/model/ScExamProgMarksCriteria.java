package com.ousl.examinations.model;

import jakarta.persistence.*;

@Entity
@Table(name = "sc_exam_prog_marks_critera")
public class ScExamProgMarksCriteria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "progid", nullable = false)
    private Program program;

    @Column(name = "marks", nullable = false)
    private int marks;

    @ManyToOne
    @JoinColumn(name = "criteria_id", nullable = false)
    private FinalExamCriteria finalExamCriteria;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public FinalExamCriteria getFinalExamCriteria() {
        return finalExamCriteria;
    }

    public void setFinalExamCriteria(FinalExamCriteria finalExamCriteria) {
        this.finalExamCriteria = finalExamCriteria;
    }
}
