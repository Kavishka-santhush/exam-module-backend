
package com.ousl.examinations.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "ScheduledExam")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduledExam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String program;
    private String batch;
    private String subject;

    private LocalDate date;
    private LocalTime time;

    private String structure;
    private String location;
}

