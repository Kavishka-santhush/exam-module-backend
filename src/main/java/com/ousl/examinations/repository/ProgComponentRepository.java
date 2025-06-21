package com.ousl.examinations.repository;

import com.ousl.examinations.model.ProgComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgComponentRepository extends JpaRepository<ProgComponent, Long> {

    List<ProgComponent> findByProgramId(Long programId);

    ProgComponent findByProgramIdAndComponentId(Long programId, Long componentId);
}
