package com.ousl.examinations.service.Impl;

import com.ousl.examinations.model.Program;
import com.ousl.examinations.model.Subject;
import com.ousl.examinations.repository.ProgramRepository;
import com.ousl.examinations.repository.SubjectRepository;
import com.ousl.examinations.service.SubjectService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional

public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ProgramRepository programRepository;


    @Override
    public Subject createSubject(Subject subject) {
        Subject newSubject = new Subject();
        System.out.println(subject.getProgram());
        System.out.println(subject.getProgram().getId());
        Program program = programRepository.findById(subject.getProgram().getId()).orElse(null);

        newSubject.setName(subject.getName());
        newSubject.setProgram(program);
        System.out.println(newSubject.toString());
        try{
            newSubject = subjectRepository.save(newSubject);
        }catch(Exception e){
            System.out.println(e.getClass().getName());
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            System.out.println(e.getStackTrace());
        }
        return newSubject;

    }

    @Override
    public Subject updateSubject(Long id,Subject subject) {
        Subject currentSubject = subjectRepository.getReferenceById(id);
        currentSubject.setName(subject.getName());
        return subjectRepository.save(currentSubject);
    }

    @Override
    @DeleteMapping
    public boolean deleteSubject(Long id) {
        Subject currentSubject = subjectRepository.getReferenceById(id);
        subjectRepository.delete(currentSubject);
        return false;
    }

    @Override
    public List<Subject> getAllSubjects() {
        List<Subject> subjects = new ArrayList<>();
        try{
            subjects = subjectRepository.findAll();
        }catch(Exception e){
            System.out.println(e.getClass().getName());
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            System.out.println(e.getStackTrace());
        }
        return subjects;
    }

    @Override
    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subject not found with id: " + id));
    }

    @Override
    public List<Subject> getSubjectsByProgramId(Long programId) {
        Program program = programRepository.findById(programId)
                .orElseThrow(() -> new EntityNotFoundException("Program not found with id: " + programId));
        return subjectRepository.findByProgram(program);
    }
}
