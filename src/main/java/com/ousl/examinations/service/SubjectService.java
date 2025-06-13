package com.ousl.examinations.service;

import com.ousl.examinations.model.Subject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubjectService {

    Subject createSubject(Subject subject);
    Subject updateSubject(Long id,Subject subject);
    boolean deleteSubject(Long id);
    List<Subject> getAllSubjects();
    Subject getSubjectById(Long id);
    List<Subject> getSubjectsByProgramId(Long programId);
}

