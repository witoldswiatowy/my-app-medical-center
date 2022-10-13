package com.project.spring.service;

import com.project.spring.model.DoctorEntity;
import com.project.spring.model.dto.DoctorDto;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;

/**
 * API interface for performing query operations and modifying operations on the {@link DoctorEntity} entities.
 */
public interface DoctorService {

    /**
     * Method to searching doctor by id.
     *
     * @param doctorId id of searching doctor
     * @return found {@link DoctorDto} or when not found any doctor then the implementation might throw an {@link EntityNotFoundException}
     */
    DoctorDto findDoctorById(Long doctorId);

    /**
     * Method to get all doctors.
     *
     * @return found {@link Set<DoctorDto>} of all doctors
     */
    List<DoctorDto> getAllDoctors();

    /**
     * Persists the passed doctor.
     * If the doctor has already DB ID assigned, then the implementation might throw an {@link IllegalArgumentException}.
     *
     * @param doctorDto - params of doctor to create
     * @return created {@link DoctorDto}
     */
    DoctorDto hireDoctor(DoctorDto doctorDto);

    /**
     * Update the passed doctor.
     * If the doctor does not exist in DB, then the implementation might throw an {@link IllegalArgumentException}.
     *
     * @param doctorDto - params of doctor to create
     * @return updated {@link DoctorDto}
     */
    DoctorDto updateDoctor(DoctorDto doctorDto);

    /**
     * Delete the passed doctor.
     * If the doctor does not exist in DB, then the implementation might throw an {@link EntityNotFoundException}.
     *
     * @param doctorId - id of doctor to delete
     */
    void fireDoctorById(Long doctorId);

    /**
     * Method to searching doctor who has the fewest visits in period time.
     *
     * @param from - start of period time
     * @param to   - end of period time
     * @return found {@link DoctorDto} or when not found any doctor then the implementation might throw an {@link NotFoundException}
     */

}
