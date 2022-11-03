package com.project.spring.service.impl;

import com.project.spring.model.ApplicationUserRole;
import com.project.spring.model.dto.ApplicationUserDto;
import com.project.spring.model.dto.CreateUserRequest;
import com.project.spring.model.dto.UpdateUserRequest;
import com.project.spring.model.enums.Sex;
import com.project.spring.model.mapper.ApplicationUserMapper;
import com.project.spring.model.ApplicationUser;
import com.project.spring.repository.ApplicationUserRepository;
import com.project.spring.repository.ApplicationUserRoleRepository;
import com.project.spring.repository.DoctorRepository;
import com.project.spring.service.ApplicationUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationUserServiceImpl implements ApplicationUserService {
    @Value("${application.default.newUserRole:ROLE_USER}")
    private String defaultNewUserRole;

    private final ApplicationUserRoleRepository applicationUserRoleRepository;
    private final ApplicationUserRepository applicationUserRepository;
    private final DoctorRepository doctorRepository;
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

    @Override
    public ApplicationUserDto getUserDetails(Long userId) {
        ApplicationUser user = applicationUserRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new);

        return applicationUserMapper.mapApplicationUserToDto(user);
    }

    @Override
    public void deleteUserById(Long userId) {
        if (applicationUserRepository.findById(userId).isPresent()) {
            log.info("Deleting user with id {}", userId);
            applicationUserRepository.deleteById(userId);
            return;
        }
        log.error("User does not exist in DB, delete is not permitted!");
        throw new EntityNotFoundException("User with id: " + userId + " does not exist in DB, delete is not permitted!");
    }

    @Override
    public ApplicationUserDto updateUser(Long id, UpdateUserRequest request) {
        final Long updateDoctorId = request.getDoctorId();
        final String updateName = request.getName();
        final String updateSurname = request.getSurname();
        final String updatePhoneNumber = request.getPhoneNumber();
        final String updateEmail = request.getEmail();
        final LocalDate updateBirthDate = request.getBirthDate();
        final Sex updateSex = request.getSex();
        final Set<ApplicationUserRole> updateRoles = request.getRoles();

        ApplicationUser applicationUserUpdated = applicationUserRepository.findById(id)
                .map(user -> {
                    user.setDoctorEntity(updateDoctorId != null ?
                            doctorRepository.findById(updateDoctorId)
                                    .orElseThrow(EntityNotFoundException::new)
                            : user.getDoctorEntity());
                    user.setFirstName(updateName != null ? updateName : user.getFirstName());
                    user.setLastName(updateSurname != null ? updateSurname : user.getLastName());
                    user.setPhoneNumber(updatePhoneNumber != null ? updatePhoneNumber : user.getPhoneNumber());
                    user.setEmail(updateEmail != null ? updateEmail : user.getEmail());
                    user.setBirthDate(updateBirthDate != null ? updateBirthDate : user.getBirthDate());
                    user.setSex(updateSex != null ? updateSex : user.getSex());
                    user.getRoles().addAll(updateRoles);
                    return user;
                })
                .orElseThrow(EntityNotFoundException::new);
        return applicationUserMapper.mapApplicationUserToDto(applicationUserRepository.save(applicationUserUpdated));
    }
}
