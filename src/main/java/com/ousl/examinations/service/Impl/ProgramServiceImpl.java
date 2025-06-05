package com.ousl.examinations.service.Impl;

import com.ousl.examinations.model.Program;
import com.ousl.examinations.repository.ProgramRepository;
import com.ousl.examinations.service.ProgramService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramServiceImpl implements ProgramService {
    public ProgramRepository programRepository;


    @Override
    public Program createProgram(Program program) {
        Program newProgram=new Program();
        newProgram.setName(program.getName());
//        newProgram.setProgramCode(program.getProgramCode());

        return programRepository.save(newProgram);

    }

    @Override
    public Program updateProgram(Long id,Program program) {
        Program currentProgram=programRepository.getReferenceById(id);
        currentProgram.setName(program.getName());
//        currentProgram.setProgramCode(program.getProgramCode());
//
        return programRepository.save(currentProgram);

    }

    @Override
    public boolean deleteProgram(Long id) {
        Program currentProgram=programRepository.getReferenceById(id);
        programRepository.delete(currentProgram);
        return false;
    }

    @Override
    public List<Program> getAllPrograms() {
        return programRepository.findAll();

    }

    @Override
    public Program getProgramById(Long id) {
        Program currentProgram=programRepository.getReferenceById((long) id);
        return currentProgram;
    }
}