package com.project.spring.model.dto;

import com.project.spring.model.enums.Status;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisitDto {

    private Long id;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private Long version;

    private Status status;
    private LocalDateTime timeInDuty;
    private BigDecimal price;
    private DoctorDto doctor;
    private ApplicationUserDto applicationUser;
    private DutyDto duty;

}
