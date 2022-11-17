package com.project.spring.controller;

import com.project.spring.component.PrincipalComponent;
import com.project.spring.model.dto.AddDutyRequest;
import com.project.spring.model.dto.DutyDto;
import com.project.spring.service.DutyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/duty")
@RequiredArgsConstructor
public class DutyController {

    private final DutyService dutyService;
    private final PrincipalComponent principalComponent;

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN') || hasRole('DOCTOR')")
    public List<DutyDto> getAllDuties() {
        log.info("getAllDuties called");
        return dutyService.getDutiesList();
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') || hasRole('DOCTOR')")
    public DutyDto addDuty(
            @Valid @RequestBody AddDutyRequest request,
            @PathVariable(name = "id") Long userId,
            UsernamePasswordAuthenticationToken principal) {
        log.info("addDuty called");
        return dutyService.addDuty(principalComponent.getUser(principal, userId).getId(), request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteDutyById(@PathVariable Long id){
        log.info("deleteDutyById called");
        dutyService.deleteDuty(id);
    }
}
