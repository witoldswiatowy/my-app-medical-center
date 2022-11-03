package com.project.spring.model.dto;

import com.project.spring.model.ApplicationUserRole;
import com.project.spring.model.enums.Sex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
    private Long id;

    private Long doctorId;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private LocalDate birthDate;
    private Sex sex;
    private Set<ApplicationUserRole> roles;
}
