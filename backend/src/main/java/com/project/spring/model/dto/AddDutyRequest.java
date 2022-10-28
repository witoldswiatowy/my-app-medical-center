package com.project.spring.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddDutyRequest {

    @Future
    private LocalDateTime dutyFrom;
    @Future
    private LocalDateTime dutyTo;
    @NotNull
    @Positive
    private Long doctorId;
}
