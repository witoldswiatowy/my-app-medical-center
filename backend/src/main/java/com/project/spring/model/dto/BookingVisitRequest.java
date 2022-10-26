package com.project.spring.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class BookingVisitRequest {

    private LocalDateTime timeInDuty;
    private String specialization; //może wyszukiwanie po pecjalizacji
    private Long doctorId;
    private Long patientId;
    private Long dutyId;
}
