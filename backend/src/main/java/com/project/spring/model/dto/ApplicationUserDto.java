package com.project.spring.model.dto;

import com.project.spring.model.enums.Sex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationUserDto {
    private Long id;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
//    private Long version;
    private String login;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private LocalDate birthDate;
    private Sex sex;
    private List<String> roles;
}
