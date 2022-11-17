package com.project.spring.service.impl;

import com.project.spring.exception.ParamsIsIncorrectException;
import com.project.spring.model.ApplicationUser;
import com.project.spring.model.DoctorEntity;
import com.project.spring.model.DutyEntity;
import com.project.spring.model.dto.AddDutyRequest;
import com.project.spring.model.dto.DutyDto;
import com.project.spring.model.mapper.DutyMapper;
import com.project.spring.repository.ApplicationUserRepository;
import com.project.spring.repository.DoctorRepository;
import com.project.spring.repository.DutyRepository;
import com.project.spring.service.DutyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DutyServiceImpl implements DutyService {

    public static final LocalTime TIME_MAX_FOR_TO = LocalTime.of(18, 0);
    public static final LocalTime TIME_MIN_FOR_FROM = LocalTime.of(8, 0);

    private final ApplicationUserRepository applicationUserRepository;
    private final DutyRepository dutyRepository;
    private final DoctorRepository doctorRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DutyDto> getDutiesList() {
        return dutyRepository.findAll()
                .stream()
                .map(DutyMapper::toDutyDto)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DutyDto addDuty(Long userId, AddDutyRequest request) {
        DoctorEntity doctorEntity;
        if (request.getDoctorId() > 0) {
            doctorEntity = doctorRepository.findById(request.getDoctorId())
                    .orElseThrow(() -> new EntityNotFoundException("Doctor with id: " + request.getDoctorId() + " does not exist in DB, delete is not permitted!"));
            validateCorrectDtoForCrud(request);
        } else {
            ApplicationUser owner = applicationUserRepository.findById(userId)
                    .orElseThrow(EntityNotFoundException::new);
            Long ownerDoctorId = owner.getDoctorEntity().getId();
            doctorEntity = doctorRepository.findById(ownerDoctorId)
                    .orElseThrow(() -> new EntityNotFoundException("Doctor with id: " + ownerDoctorId + " does not exist in DB, delete is not permitted!"));
            request.setDoctorId(ownerDoctorId);
            validateCorrectDtoForCrud(request);
        }

        DutyEntity dutyEntity = DutyMapper.requestToDutyEntity(request);
        dutyEntity.setDoctor(doctorEntity);

        DutyEntity savedDutyEntity = dutyRepository.save(dutyEntity);
        log.info("Create duty {}", savedDutyEntity);
        return DutyMapper.toDutyDto(savedDutyEntity);
    }

    @Override
    public void deleteDuty(Long dutyId) {
        if (dutyRepository.findById(dutyId).isPresent()) {
            log.info("Deleting duty with id {}", dutyId);
            dutyRepository.deleteById(dutyId);
            return;
        }
        log.error("Duty does not exist in DB, delete is not permitted!");
        throw new EntityNotFoundException("Duty with id: " + dutyId + " does not exist in DB, delete is not permitted!");
    }


    private void validateCorrectDtoForCrud(AddDutyRequest request) {
        if (request == null) {
            log.error("Object what you want to save is null!");
            throw new IllegalArgumentException();
        }
        validateForDateTime(request);
        validateForDutyAtSameDay(request);


        if (request.getDoctorId() == null) {
            log.error("Duty must have a doctor id!");
            throw new IllegalArgumentException();
        } else {
        }
    }

    private static void validateForDateTime(AddDutyRequest request) {
        LocalDateTime dutyFrom = request.getDutyFrom();
        LocalDateTime dutyTo = request.getDutyTo();
        if (dutyFrom == null || dutyTo == null) {
            log.error("Duty need declared date from and to!");
            throw new IllegalArgumentException();
        }
        if (!dutyFrom.isBefore(dutyTo)) {
            log.error("\"Date from\" must be before \"date to\"!");
            throw new ParamsIsIncorrectException("\"Date from\" must be before \"date to\"!");
        }
        if (dutyTo.isAfter(LocalDateTime.of(dutyTo.toLocalDate(), TIME_MAX_FOR_TO))) {
            log.error("Time for ended duty is too late!");
            throw new ParamsIsIncorrectException("Time for ended duty is too late!");
        }
        if (dutyFrom.isBefore(LocalDateTime.of(dutyFrom.toLocalDate(), TIME_MIN_FOR_FROM))) {
            log.error("Time for start duty is too early!");
            throw new ParamsIsIncorrectException("Time for start duty is too early!");
        }
    }

    private void validateForDutyAtSameDay(AddDutyRequest request) {
        DoctorEntity doctorEntity = doctorRepository.findById(request.getDoctorId()).get();
        List<DutyEntity> actualDutyByDoctor = dutyRepository.findAllByDoctorAndDutyFromAfter(doctorEntity, request.getDutyFrom().minusDays(1));
        List<DutyEntity> dutyWithSameDay = actualDutyByDoctor.stream()
                .filter(dutyEntity -> dutyEntity
                        .getDutyFrom()
                        .toLocalDate()
                        .equals(request
                                .getDutyFrom()
                                .toLocalDate()))
                .collect(Collectors.toList());
        if (!dutyWithSameDay.isEmpty()) {
            throw new ParamsIsIncorrectException("Doctor can not have more than one duty in this same day!");
        }
    }
}
