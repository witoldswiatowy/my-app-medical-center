package com.project.spring.controller;

import com.project.spring.component.PrincipalComponent;
import com.project.spring.model.dto.AddDutyRequest;
import com.project.spring.model.dto.DutyDto;
import com.project.spring.service.DutyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/duty")
@RequiredArgsConstructor
public class DutyController {

    private final DutyService dutyService;
    private final PrincipalComponent principalComponent;

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public List<DutyDto> getAllDuties() {
        log.info("getAllDuties called");
        return dutyService.getDutiesList();
    }

    @PostMapping
    public DutyDto addDuty(@RequestBody AddDutyRequest request) {
        log.info("addDuty called");
        return dutyService.addDuty(request);
    }
}
