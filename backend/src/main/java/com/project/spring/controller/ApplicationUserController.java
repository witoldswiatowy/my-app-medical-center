package com.project.spring.controller;

import com.project.spring.model.dto.ApplicationUserDto;
import com.project.spring.model.dto.CreateUserRequest;
import com.project.spring.service.ApplicationUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/api/user")
@RequiredArgsConstructor
public class ApplicationUserController {
    private final ApplicationUserService applicationUserService;

    @GetMapping()
    @CrossOrigin()
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN') && hasRole('MODERATOR')")
    public List<ApplicationUserDto> getListOfUsers(){
        log.info("getListOfUsers called from ApplicationUserController");
        List<ApplicationUserDto> applicationUserDtos = applicationUserService.listUsers();
        return applicationUserDtos;
    }

    @PostMapping()
    @CrossOrigin()
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationUserDto postNewUser(@RequestBody CreateUserRequest request){
        log.info("postNewUser called from ApplicationUserController");
        return applicationUserService.addUser(request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUserById(@PathVariable Long id){
        log.info("deleteUserById called");
        applicationUserService.deleteUserById(id);
    }
}







