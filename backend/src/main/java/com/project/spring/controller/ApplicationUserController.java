package com.project.spring.controller;

import com.project.spring.model.dto.ApplicationUserDto;
import com.project.spring.model.dto.CreateUserRequest;
import com.project.spring.model.dto.UpdateUserRequest;
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
    public List<ApplicationUserDto> getListOfUsers() {
        log.info("getListOfUsers called from ApplicationUserController");
        List<ApplicationUserDto> applicationUserDtos = applicationUserService.listUsers();
        return applicationUserDtos;
    }

    @GetMapping("/{identifier}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN') && hasRole('MODERATOR')")
    public ApplicationUserDto getUserDetails(@PathVariable(name = "identifier") Long id) {
        log.info("getUserDetails called from ApplicationUserController");
        return applicationUserService.getUserDetails(id);
    }

    @PostMapping()
    @CrossOrigin()
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationUserDto postNewUser(@RequestBody CreateUserRequest request) {
        log.info("postNewUser called from ApplicationUserController");
        return applicationUserService.addUser(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUserById(@PathVariable Long id) {
        log.info("deleteUserById called from ApplicationUserController");
        applicationUserService.deleteUserById(id);
    }

//    @PutMapping("/{identifier}")
    @PutMapping()
    public ApplicationUserDto updateUser(
//            @PathVariable(name = "identifier") Long id,
            @RequestBody UpdateUserRequest request){
        log.info("updateUser called from ApplicationUserController");
//        return applicationUserService.updateUser(id, request);
        return applicationUserService.updateUser(request.getId(), request);
    }
}







