package com.project.spring.controller;

import com.project.spring.model.dto.AddDoctorRequest;
import com.project.spring.model.dto.DoctorDto;
import com.project.spring.service.DoctorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
    public List<DoctorDto> getListOfDoctor() {
        log.info("getListOfDoctor called from DoctorController");
        return doctorService.getAllDoctors();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public DoctorDto addDoctor(@Valid  @RequestBody AddDoctorRequest request) {
        log.info("addDoctor called");
        return doctorService.hireDoctor(request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteDoctorById(@PathVariable Long id){
        log.info("deleteDoctorById called");
        doctorService.fireDoctorById(id);
    }
}
