package com.project.spring.model.dto;

import com.project.spring.model.enums.MedicalSpecialization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddDoctorRequest {

    @NotBlank
    @Size(min = 2, max = 20)
    private String firstName;
    @NotBlank
    @Size(min = 2, max = 20)
    private String lastName;
    @NotBlank
    @Pattern(regexp = "^\\d{9}$")
    private String phoneNumber;
    @Email
    private String email;
    private String description;
    private String imgUrl;
    private MedicalSpecialization specialization;
    @Positive
    private BigDecimal hourlyRate;
    @Positive
    private Long clinicId;
}
