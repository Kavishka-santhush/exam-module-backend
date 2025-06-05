package com.ousl.examinations.service;

import com.ousl.examinations.model.Program;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProgramService {
    Program createProgram(Program program);
    Program updateProgram(Long id,Program program);
    boolean deleteProgram(Long id);
    List<Program> getAllPrograms();
    Program getProgramById(Long id);

}
