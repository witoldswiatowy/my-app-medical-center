package com.project.spring.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class BookingVisitRequest {
//todo z frontendu wysyła się wybraną godzine z listy z wybranego duty, nie ma datepickera, on moze byc w tworzeniu duty
    private LocalDateTime timeInDuty; //to tez nie bedzie date na pewno
//    private String specialization; //może wyszukiwanie po pecjalizacji
    private Long doctorId;
    private Long patientId;
    private Long dutyId;
}
