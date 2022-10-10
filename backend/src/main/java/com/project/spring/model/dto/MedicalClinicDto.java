package com.project.spring.model.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class MedicalClinicDto {

    private Long id;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private Long version;
    private String name;
    private Set<DoctorDto> doctors;

}
