package com.ousl.examinations.service;
import com.ousl.examinations.model.ScheduledExam;
import com.ousl.examinations.repository.ExamScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamScheduleService {

    @Autowired
    private ExamScheduleRepository examScheduleRepository;

    public ScheduledExam saveExamSchedule(ScheduledExam scheduledExam) {
        return examScheduleRepository.save(scheduledExam);
    }

    public List<ScheduledExam> getAllExamSchedules() {
        return examScheduleRepository.findAll();
    }
    public void deleteExamSchedule(Long id) {
        examScheduleRepository.deleteById(id);
    }

    public ScheduledExam updateExamSchedule(Long id, ScheduledExam updatedExam) {
        return examScheduleRepository.findById(id)
                .map(existingExam -> {
                    existingExam.setProgram(updatedExam.getProgram());
                    existingExam.setBatch(updatedExam.getBatch());
                    existingExam.setSubject(updatedExam.getSubject());
                    existingExam.setDate(updatedExam.getDate());
                    existingExam.setTime(updatedExam.getTime());
                    existingExam.setStructure(updatedExam.getStructure());
                    existingExam.setLocation(updatedExam.getLocation());
                    return examScheduleRepository.save(existingExam);
                })
                .orElseThrow(() -> new RuntimeException("Exam not found with ID: " + id));
    }

}




