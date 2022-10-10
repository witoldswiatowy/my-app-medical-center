package com.project.spring.model.mapper;

import com.project.spring.model.ApplicationUser;
import com.project.spring.model.dto.ApplicationUserDto;

public class ApplicationUserMapper2 {

    public static ApplicationUserDto toApplicationUserDto(ApplicationUser applicationUser) {
        if (applicationUser == null) {
            return null;
        }
        return ApplicationUserDto.builder()
                .id(applicationUser.getId())
//                .createDate(applicationUser.getCreateDate())
//                .updateDate(applicationUser.getUpdateDate())
//                .version(applicationUser.getVersion())
                .name(applicationUser.getFirstName())
                .surname(applicationUser.getLastName())
//                .phoneNumber(applicationUser.getPhoneNumber())
//                .email(applicationUser.getEmail())
//                .birthDate(applicationUser.getBirthDate())
//                .sex(applicationUser.getSex())
                .build();
    }

    public static ApplicationUser toApplicationUser(ApplicationUserDto applicationUserDto) {
        if (applicationUserDto == null) {
            return null;
        }
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setId(applicationUserDto.getId());
//        applicationUser.setCreateDate(applicationUserDto.getCreateDate());
//        applicationUser.setUpdateDate(applicationUserDto.getUpdateDate());
//        applicationUser.setVersion(applicationUserDto.getVersion());
        applicationUser.setFirstName(applicationUserDto.getName());
        applicationUser.setLastName(applicationUserDto.getSurname());
//        applicationUser.setPhoneNumber(applicationUserDto.getPhoneNumber());
//        applicationUser.setEmail(applicationUserDto.getEmail());
//        applicationUser.setBirthDate(applicationUserDto.getBirthDate());
//        applicationUser.setSex(applicationUserDto.getSex());
        return applicationUser;
    }
}
