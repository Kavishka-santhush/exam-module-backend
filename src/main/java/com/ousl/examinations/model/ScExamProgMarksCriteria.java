package com.ousl.examinations.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sc_exam_prog_marks_critera")
public class ScExamProgMarksCriteria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "progid", nullable = false)
    private Program program;

    @ManyToOne
    @JoinColumn(name = "criteria_id", nullable = false)
    private FinalExamCriteria finalExamCriteria;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;

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



    public FinalExamCriteria getFinalExamCriteria() {
        return finalExamCriteria;
    }

    public void setFinalExamCriteria(FinalExamCriteria finalExamCriteria) {
        this.finalExamCriteria = finalExamCriteria;
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
