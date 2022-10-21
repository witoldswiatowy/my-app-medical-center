package com.project.spring.model.dto;

import com.project.spring.model.enums.MedicalSpecialization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddDoctorRequest {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private MedicalSpecialization specialization;
    private BigDecimal hourlyRate;
    private Long clinicId;
}
