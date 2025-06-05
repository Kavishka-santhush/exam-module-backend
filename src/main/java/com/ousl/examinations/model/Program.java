package com.ousl.examinations.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String programCode;

//    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Subject> subjects;
}