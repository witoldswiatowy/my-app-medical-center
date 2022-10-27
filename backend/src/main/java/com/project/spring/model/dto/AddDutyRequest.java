package com.project.spring.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddDutyRequest {

    private LocalDateTime dutyFrom;
    private LocalDateTime dutyTo;
    private Long doctorId;
}
