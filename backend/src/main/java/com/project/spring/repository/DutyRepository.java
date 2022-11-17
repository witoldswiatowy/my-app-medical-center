package com.project.spring.repository;

import com.project.spring.model.DoctorEntity;
import com.project.spring.model.DutyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface DutyRepository extends JpaRepository<DutyEntity, Long> {

    List<DutyEntity> findAllByDoctorAndDutyFromAfter(DoctorEntity doctor, LocalDateTime dutyFrom);

}
