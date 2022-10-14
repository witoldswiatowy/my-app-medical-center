package com.project.spring.repository;


import com.project.spring.model.ApplicationUser;
import com.project.spring.model.VisitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitRepository extends JpaRepository<VisitEntity, Long> {

    List<VisitEntity> findAllByUser(ApplicationUser user);
}
