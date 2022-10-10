package com.project.spring.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationUserDto {
    private Long id;
    private String login;
    private String name;
    private String surname;
    private List<String> roles;
}
