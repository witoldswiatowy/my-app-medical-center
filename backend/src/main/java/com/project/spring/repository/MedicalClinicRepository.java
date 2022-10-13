package com.project.spring.repository;


import com.project.spring.model.MedicalClinicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalClinicRepository extends JpaRepository<MedicalClinicEntity, Long> {
}
