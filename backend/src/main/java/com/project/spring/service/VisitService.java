package com.project.spring.service;

import com.project.spring.model.dto.VisitDto;

import java.util.List;

public interface VisitService {

    List<VisitDto> getVisitsList();

    List<VisitDto> getVisitsList(Long userId);
}
