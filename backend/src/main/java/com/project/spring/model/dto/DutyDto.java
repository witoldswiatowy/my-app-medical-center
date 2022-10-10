package com.project.spring.model.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class DutyDto {

    private Long id;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private Long version;
    private LocalDateTime dutyFrom;
    private LocalDateTime dutyTo;
    private DoctorDto doctor;
    private Set<VisitDto> visits;

}
