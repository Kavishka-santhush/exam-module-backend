package com.ousl.examinations.repository;
import com.ousl.examinations.model.ScheduledExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamScheduleRepository extends JpaRepository <ScheduledExam, Long>{

}
