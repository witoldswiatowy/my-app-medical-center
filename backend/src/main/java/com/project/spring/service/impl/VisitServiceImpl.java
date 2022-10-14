package com.project.spring.service.impl;

import com.project.spring.model.ApplicationUser;
import com.project.spring.model.dto.VisitDto;
import com.project.spring.model.mapper.VisitMapper;
import com.project.spring.repository.ApplicationUserRepository;
import com.project.spring.repository.VisitRepository;
import com.project.spring.service.VisitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {

    private final ApplicationUserRepository applicationUserRepository;
    private final VisitRepository visitRepository;

    @Override
    public List<VisitDto> getVisitsList() {
        return visitRepository.findAll()
                .stream()
                .map(visit -> {
                    return VisitMapper.toVisitDto(visit, visit.getApplicationUser());
                }).collect(Collectors.toList());    }

    @Override
    public List<VisitDto> getVisitsList(Long userId) {
        ApplicationUser applicationUser = applicationUserRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new);

        return visitRepository.findAllByUser(applicationUser)
                .stream()
                .map(visit -> {
                    return VisitMapper.toVisitDto(visit, visit.getApplicationUser());
                }).collect(Collectors.toList());
    }
}
