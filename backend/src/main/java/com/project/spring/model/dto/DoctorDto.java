package com.project.spring.model.dto;

import com.project.spring.model.DutyEntity;
import com.project.spring.model.VisitEntity;
import com.project.spring.model.enums.MedicalSpecialization;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class DoctorDto {

    private Long id;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private Long version;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String description;
    private String imgUrl;
    private MedicalSpecialization specialization;
    private BigDecimal hourlyRate;
    private MedicalClinicDto clinic;
    private Set<DutyEntity> duties;
    private Set<VisitEntity> visits;

}
