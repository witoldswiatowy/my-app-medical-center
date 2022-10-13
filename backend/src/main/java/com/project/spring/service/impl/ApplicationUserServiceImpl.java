package com.project.spring.service.impl;

import com.project.spring.model.dto.ApplicationUserDto;
import com.project.spring.model.dto.CreateUserRequest;
import com.project.spring.model.mapper.ApplicationUserMapper;
import com.project.spring.model.ApplicationUser;
import com.project.spring.repository.ApplicationUserRepository;
import com.project.spring.repository.ApplicationUserRoleRepository;
import com.project.spring.service.ApplicationUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationUserServiceImpl implements ApplicationUserService {
    @Value("${application.default.newUserRole:ROLE_USER}")
    private String defaultNewUserRole;

    private final ApplicationUserRoleRepository applicationUserRoleRepository;
    private final ApplicationUserRepository applicationUserRepository;
    private final ApplicationUserMapper applicationUserMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public ApplicationUserDto addUser(CreateUserRequest request) {
        ApplicationUser newUser = applicationUserMapper.mapCreateUserRequestToApplicationUser(request);

        newUser.setPassword(passwordEncoder.encode(request.getPass()));
        newUser.setRoles(
                new HashSet<>(
                        Collections.singletonList(applicationUserRoleRepository
                                .findByName(defaultNewUserRole)
                                .orElseThrow(EntityNotFoundException::new))));

        return applicationUserMapper.mapApplicationUserToDto(applicationUserRepository.save(newUser));
    }

    @Override
    public List<ApplicationUserDto> listUsers() {
        return applicationUserRepository.findAll()
                .stream()
                .map(applicationUserMapper::mapApplicationUserToDto)
                .collect(Collectors.toList());
    }
}
