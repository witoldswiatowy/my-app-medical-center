package com.project.spring.controller;

import com.project.spring.component.PrincipalComponent;
import com.project.spring.model.dto.VisitDto;
import com.project.spring.service.VisitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/visit")
@RequiredArgsConstructor
public class VisitController {

    private final VisitService visitService;
    private final PrincipalComponent principalComponent;

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public List<VisitDto> getAllVisits() {
        log.info("getAllVisits called");
        return visitService.getVisitsList();
    }

//    @GetMapping("/{userId}")
//    @PreAuthorize("isAuthenticated()")
//    public List<VisitDto> getUserSales(UsernamePasswordAuthenticationToken principal, @PathVariable Long userId) {
//        log.info("getUserSales called");
//        principalComponent.getUser(principal, userId);
//
//        return visitService.getVisitsList(userId);
//    }
}
