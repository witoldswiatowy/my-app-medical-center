package com.project.spring.service.impl;

import com.project.spring.model.ApplicationUser;
import com.project.spring.model.DoctorEntity;
import com.project.spring.model.DutyEntity;
import com.project.spring.model.VisitEntity;
import com.project.spring.model.dto.BookingVisitRequest;
import com.project.spring.model.dto.VisitDto;
import com.project.spring.model.enums.Status;
import com.project.spring.model.mapper.VisitMapper;
import com.project.spring.repository.ApplicationUserRepository;
import com.project.spring.repository.DoctorRepository;
import com.project.spring.repository.DutyRepository;
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
    private final DoctorRepository doctorRepository;
    private final DutyRepository dutyRepository;

    @Override
    public List<VisitDto> getVisitsList() {
        return visitRepository.findAll()
                .stream()
                .map(visit -> VisitMapper.toVisitDto(visit, visit.getApplicationUser()))
                .collect(Collectors.toList());
    }

//    @Override
//    public List<VisitDto> getVisitsList(Long userId) {
//        ApplicationUser applicationUser = applicationUserRepository.findById(userId)
//                .orElseThrow(EntityNotFoundException::new);
//
//        return visitRepository.findAllByUser(applicationUser)
//                .stream()
//                .map(visit -> {
//                    return VisitMapper.toVisitDto(visit, visit.getApplicationUser());
//                }).collect(Collectors.toList());
//    }

    @Override
    public VisitDto bookingVisit(BookingVisitRequest request) {
        VisitEntity visitEntity = new VisitEntity();
        visitEntity.setStatus(Status.WAITING);
        visitEntity.setTimeInDuty(request.getTimeInDuty());

        DoctorEntity doctorEntity = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new EntityNotFoundException("Doctor with id: " + request.getDoctorId() + " does not exist in DB, delete is not permitted!"));
        visitEntity.setDoctor(doctorEntity);
        ApplicationUser applicationUser = applicationUserRepository.findById(request.getPatientId())
                .orElseThrow(() -> new EntityNotFoundException("Patient with id: " + request.getPatientId() + " does not exist in DB, delete is not permitted!"));
        visitEntity.setApplicationUser(applicationUser);
        DutyEntity dutyEntity = dutyRepository.findById(request.getDutyId())
                .orElseThrow(() -> new EntityNotFoundException("Duty with id: " + request.getDutyId() + " does not exist in DB, delete is not permitted!"));
        visitEntity.setDuty(dutyEntity);

        VisitEntity savedVisitEntity = visitRepository.save(visitEntity);
        log.info("Create visit {}", savedVisitEntity);
        return VisitMapper.toVisitDto(savedVisitEntity);
    }
}
