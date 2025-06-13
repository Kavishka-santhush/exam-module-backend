package com.ousl.examinations.controller;

import com.ousl.examinations.model.ScheduledExam;
import com.ousl.examinations.service.ExamScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/schedule")
public class ExamScheduleController {

    @Autowired
    private ExamScheduleService examScheduleService;

    @PostMapping
    public ScheduledExam createExamSchedule(@RequestBody ScheduledExam examSchedule) {
        return examScheduleService.saveExamSchedule(examSchedule);
    }
    @GetMapping
    public List<ScheduledExam> getAllExamSchedules() {
        return examScheduleService.getAllExamSchedules();
    }

    @PutMapping("/{id}")
    public ScheduledExam updateExamSchedule(@PathVariable Long id, @RequestBody ScheduledExam updatedExam) {
        return examScheduleService.updateExamSchedule(id, updatedExam);
    }

    @DeleteMapping("/{id}")
    public void deleteExamSchedule(@PathVariable Long id) {
        examScheduleService.deleteExamSchedule(id);
    }

}

