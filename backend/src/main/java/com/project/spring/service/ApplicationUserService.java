package com.project.spring.service;


import com.project.spring.model.dto.ApplicationUserDto;
import com.project.spring.model.dto.CreateUserRequest;
import com.project.spring.model.dto.UpdateUserRequest;

import java.util.List;

public interface ApplicationUserService {

    ApplicationUserDto addUser(CreateUserRequest request);

    List<ApplicationUserDto> listUsers();

    ApplicationUserDto getUserDetails(Long id);

    void deleteUserById(Long id);

    ApplicationUserDto updateUser(Long id, UpdateUserRequest request);
}
