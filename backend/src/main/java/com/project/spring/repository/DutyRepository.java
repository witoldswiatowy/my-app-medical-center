package com.project.spring.repository;

import com.project.spring.model.DutyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DutyRepository extends JpaRepository<DutyEntity, Long> {
}
