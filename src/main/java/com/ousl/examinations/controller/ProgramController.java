package com.ousl.examinations.controller;

import com.ousl.examinations.model.Program;
import com.ousl.examinations.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programs")
@CrossOrigin(origins = "*" ,maxAge=3600)
public class ProgramController {

    @Autowired
    private ProgramService programService;

    @PostMapping
    public Program createProgram(@RequestBody Program program) {
        return programService.createProgram(program);
    }
    @GetMapping
    public List<Program> AllPrograms() {
        return programService.getAllPrograms();
    }



    @GetMapping("/{id}")
    public Program getProgramById(@PathVariable Long id) {
        return programService.getProgramById(id);
    }

    @PutMapping("/{id}")
    public Program updateProgram(@PathVariable Long id, @RequestBody Program updatedProgram) {
        return programService.updateProgram(id, updatedProgram);
    }


    @DeleteMapping("/{id}")
    public String deleteProgram(@PathVariable Long id) {
        programService.deleteProgram(id);
        return "Program deleted successfully";
    }



}
