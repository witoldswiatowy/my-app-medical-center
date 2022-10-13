package com.project.spring.service.impl;

import com.project.spring.exception.ParamsIsEmptyException;
import com.project.spring.exception.ParamsIsIncorrectException;
import com.project.spring.model.DoctorEntity;
import com.project.spring.model.MedicalClinicEntity;
import com.project.spring.model.dto.DoctorDto;
import com.project.spring.model.mapper.DoctorMapper;
import com.project.spring.model.mapper.MedicalClinicMapper;
import com.project.spring.repository.DoctorRepository;
import com.project.spring.repository.MedicalClinicRepository;
import com.project.spring.service.DoctorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    public static final String MINIMUM_HOURLY_RATE = "19.70";
    private final DoctorRepository doctorRepository;
    private final MedicalClinicRepository medicalClinicRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public DoctorDto findDoctorById(Long doctorId) {
        return DoctorMapper.toDoctorDto(doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor with id: " + doctorId + " was not found")));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DoctorDto> getAllDoctors() {
        return DoctorMapper.toDoctorDtos(doctorRepository.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DoctorDto hireDoctor(DoctorDto doctorDto) {
        validateCorrectDtoForCrud(doctorDto);
        DoctorEntity doctorEntity = DoctorMapper.toDoctorEntity(doctorDto);
        if (doctorDto.getClinic().getId() != null) {
            MedicalClinicEntity medicalClinicEntity = MedicalClinicMapper.toMedicalClinicEntity(doctorDto.getClinic());
            MedicalClinicEntity managedMedicalClinicEntity = medicalClinicRepository.save(medicalClinicEntity);
            doctorEntity.setClinic(managedMedicalClinicEntity);
        }
        DoctorEntity savedDoctorEntity = doctorRepository.save(doctorEntity);
        log.info("Create doctor {}", savedDoctorEntity);
        return DoctorMapper.toDoctorDto(savedDoctorEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DoctorDto updateDoctor(DoctorDto doctorWithChange) {
        validateCorrectDtoForCrud(doctorWithChange);
        DoctorEntity doctorEntity = doctorRepository.save(DoctorMapper.toDoctorEntity(doctorWithChange));
        log.info("Updating doctor with id {}", doctorEntity.getId());
        return DoctorMapper.toDoctorDto(doctorEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fireDoctorById(Long doctorId) {
        if (doctorRepository.findById(doctorId).isPresent()) {
            log.info("Deleting doctor with id {}", doctorId);
            doctorRepository.deleteById(doctorId);
            return;
        }
        log.error("Doctor does not exist in DB, delete is not permitted!");
        throw new EntityNotFoundException("Doctor with id: " + doctorId + " does not exist in DB, delete is not permitted!");
    }


    private void validateCorrectDtoForCrud(DoctorDto doctorDto) {
        if (doctorDto == null) {
            log.error("Object what you want to save is null!");
            throw new IllegalArgumentException();
        }
        if (doctorDto.getPhoneNumber() == null) {
            log.error("Doctor must have a phone number!");
            throw new IllegalArgumentException();
        }
        if (doctorDto.getClinic() == null) {
            log.error("Doctor must have a medical clinic!");
            throw new IllegalArgumentException();
        }
        if (doctorDto.getHourlyRate() != null) {
            if (doctorDto.getHourlyRate().compareTo(new BigDecimal(MINIMUM_HOURLY_RATE)) <= 0) {
                log.error("Hourly rate can not be lower than minimum");
                throw new ParamsIsIncorrectException("Hourly rate can not be lower than minimum");
            }
        } else {
            log.error("Doctor must have a declared hourly rate!");
            throw new IllegalArgumentException();
        }
        if (doctorDto.getSpecialization() != null) {
            log.error("Doctor need any specialization!");
            throw new ParamsIsEmptyException("Doctor need any specialization!");
        }
    }
}
