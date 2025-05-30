package com.ousl.examinations.service.Impl;

import com.ousl.examinations.model.Subject;
import com.ousl.examinations.repository.SubjectRepository;
import com.ousl.examinations.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;


    @Override
    public Subject createSubject(Subject subject) {
        Subject newSubject = new Subject();
        newSubject.setName(subject.getName());
        return subjectRepository.save(newSubject);

    }

    @Override
    public Subject updateSubject(Long id,Subject subject) {
        Subject currentSubject = subjectRepository.getReferenceById(id);
        currentSubject.setName(subject.getName());
        return subjectRepository.save(currentSubject);
    }

    @Override
    public void deleteSubject(Long id) {
        Subject currentSubject = subjectRepository.getReferenceById(id);
        subjectRepository.delete(currentSubject);
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject getSubjectById(Long id) {
        return subjectRepository.getReferenceById((long) id);
    }
}
