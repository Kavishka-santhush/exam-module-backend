package com.ousl.examinations.dto;

public class MarksCriteriaDTO {

    private Long id;
    private Long programId;
    private String componentName;
    private int minMark;
    private double percentage;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public int getMinMark() {
        return minMark;
    }

    public void setMinMark(int minMark) {
        this.minMark = minMark;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
