package com.project.spring.service.impl;

import com.project.spring.model.dto.DutyDto;
import com.project.spring.model.mapper.DutyMapper;
import com.project.spring.repository.ApplicationUserRepository;
import com.project.spring.repository.DutyRepository;
import com.project.spring.service.DutyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DutyServiceImpl implements DutyService {

    private final ApplicationUserRepository applicationUserRepository;
    private final DutyRepository dutyRepository;

    @Override
    public List<DutyDto> getDutiesList() {
        return dutyRepository.findAll()
                .stream()
                .map(DutyMapper::toDutyDto)
                .collect(Collectors.toList());
    }
}
