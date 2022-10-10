package com.project.spring.service;


import com.project.spring.model.dto.ApplicationUserDto;
import com.project.spring.model.dto.CreateUserRequest;

import java.util.List;

public interface ApplicationUserService {

    ApplicationUserDto addUser(CreateUserRequest request);
    List<ApplicationUserDto> listUsers();
}
