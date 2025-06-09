package com.ousl.examinations.service;

import com.ousl.examinations.payload.request.ProgramRequest;
import com.ousl.examinations.payload.response.ProgramResponse;

import java.util.List;

public interface ProgramService {
    ProgramResponse createProgram(ProgramRequest programRequest);
    ProgramResponse updateProgram(Long id, ProgramRequest programRequest);
    ProgramResponse getProgramById(Long id);
    List<ProgramResponse> getAllPrograms();
    void deleteProgram(Long id);
}
