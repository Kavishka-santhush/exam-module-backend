package com.ousl.examinations.repository;

import com.ousl.examinations.model.MarksCriteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarksCriteriaRepository extends JpaRepository<MarksCriteria, Long> {

    List<MarksCriteria> findByComponentId(Long componentId);

}
