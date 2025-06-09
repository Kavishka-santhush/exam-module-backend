package com.ousl.examinations.controller;

import com.ousl.examinations.payload.request.ProgramRequest;
import com.ousl.examinations.payload.response.ProgramResponse;
import com.ousl.examinations.service.ProgramService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programs")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProgramController {

    @Autowired
    private ProgramService programService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProgramResponse> createProgram(@Valid @RequestBody ProgramRequest programRequest) {
        ProgramResponse newProgram = programService.createProgram(programRequest);
        return new ResponseEntity<>(newProgram, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProgramResponse> updateProgram(
            @PathVariable Long id, 
            @Valid @RequestBody ProgramRequest programRequest) {
        ProgramResponse updatedProgram = programService.updateProgram(id, programRequest);
        return ResponseEntity.ok(updatedProgram);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProgramResponse> getProgramById(@PathVariable Long id) {
        ProgramResponse program = programService.getProgramById(id);
        return ResponseEntity.ok(program);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ProgramResponse>> getAllPrograms() {
        List<ProgramResponse> programs = programService.getAllPrograms();
        return ResponseEntity.ok(programs);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteProgram(@PathVariable Long id) {
        programService.deleteProgram(id);
        return ResponseEntity.noContent().build();
    }
}
