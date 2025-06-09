package com.ousl.examinations.service.Impl;

import com.ousl.examinations.model.Program;
import com.ousl.examinations.payload.request.ProgramRequest;
import com.ousl.examinations.payload.response.ProgramResponse;
import com.ousl.examinations.repository.ProgramRepository;
import com.ousl.examinations.service.ProgramService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProgramServiceImpl implements ProgramService {

    @Autowired
    private ProgramRepository programRepository;

    @Override
    public ProgramResponse createProgram(ProgramRequest programRequest) {
        if (programRepository.existsByProgramCode(programRequest.getProgramCode())) {
            throw new IllegalArgumentException("Program code already exists: " + programRequest.getProgramCode());
        }

        Program program = new Program();
        program.setProgramName(programRequest.getProgramName());
        program.setProgramCode(programRequest.getProgramCode());
        
        if (programRequest.getStatus() != null) {
            program.setStatus(programRequest.getStatus());
        }

        Program savedProgram = programRepository.save(program);
        
        return mapProgramToResponse(savedProgram);
    }

    @Override
    public ProgramResponse updateProgram(Long id, ProgramRequest programRequest) {
        Program program = programRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Program not found with id: " + id));

        if (!program.getProgramCode().equals(programRequest.getProgramCode()) 
                && programRepository.existsByProgramCode(programRequest.getProgramCode())) {
            throw new IllegalArgumentException("Program code already exists: " + programRequest.getProgramCode());
        }

        program.setProgramName(programRequest.getProgramName());
        program.setProgramCode(programRequest.getProgramCode());
        
        if (programRequest.getStatus() != null) {
            program.setStatus(programRequest.getStatus());
        }

        Program updatedProgram = programRepository.save(program);
        
        return mapProgramToResponse(updatedProgram);
    }

    @Override
    public ProgramResponse getProgramById(Long id) {
        Program program = programRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Program not found with id: " + id));
        
        return mapProgramToResponse(program);
    }

    @Override
    public List<ProgramResponse> getAllPrograms() {
        List<Program> programs = programRepository.findByStatusTrue();
        
        return programs.stream()
                .map(this::mapProgramToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteProgram(Long id) {
        Program program = programRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Program not found with id: " + id));
        
        program.setStatus(false);
        programRepository.save(program);
    }
    
    private ProgramResponse mapProgramToResponse(Program program) {
        ProgramResponse response = new ProgramResponse();
        response.setId(program.getId());
        response.setProgramName(program.getProgramName());
        response.setProgramCode(program.getProgramCode());
        response.setStatus(program.getStatus());
        response.setCreatedBy(program.getCreatedBy());
        response.setCreatedDate(program.getCreatedDate());
        response.setLastModifiedBy(program.getLastModifiedBy());
        response.setLastModifiedDate(program.getLastModifiedDate());
        
        return response;
    }
}
