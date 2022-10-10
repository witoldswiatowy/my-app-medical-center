package com.project.spring.model.dto;

import com.project.spring.model.enums.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    private String login;
    private String pass;

    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private LocalDate birthDate;
    private Sex sex;
}
