package com.project.spring.model.mapper;

import com.project.spring.model.ApplicationUser;
import com.project.spring.model.DoctorEntity;
import com.project.spring.model.DutyEntity;
import com.project.spring.model.VisitEntity;
import com.project.spring.model.dto.ApplicationUserDto;
import com.project.spring.model.dto.DoctorDto;
import com.project.spring.model.dto.DutyDto;
import com.project.spring.model.dto.VisitDto;

import java.util.List;
import java.util.stream.Collectors;

public class VisitMapper {

    private VisitMapper() {
    }



    public static VisitDto toVisitDto (VisitEntity visitEntity, ApplicationUser user){
        if (visitEntity == null){
            return null;
        }

        DoctorDto doctorDto = DoctorMapper.toDoctorDto(visitEntity.getDoctor());
        ApplicationUserDto applicationUserDto = ApplicationUserMapper2.toApplicationUserDto(user);
        DutyDto dutyDto = DutyMapper.toDutyDto(visitEntity.getDuty());

        return VisitDto.builder()
                .id(visitEntity.getId())
                .createDate(visitEntity.getCreateDate())
                .updateDate(visitEntity.getUpdateDate())
                .version(visitEntity.getVersion())
                .status(visitEntity.getStatus())
                .timeInDuty(visitEntity.getTimeInDuty())
                .price(visitEntity.getPrice())
                .doctor(doctorDto)
                .applicationUser(applicationUserDto)
                .duty(dutyDto)
                .build();
    }

    public static VisitDto toVisitDto (VisitEntity visitEntity){
        if (visitEntity == null){
            return null;
        }

        DoctorDto doctorDto = DoctorMapper.toDoctorDto(visitEntity.getDoctor());
        ApplicationUserDto applicationUserDto = ApplicationUserMapper2.toApplicationUserDto(visitEntity.getApplicationUser());
        DutyDto dutyDto = DutyMapper.toDutyDto(visitEntity.getDuty());

        return VisitDto.builder()
                .id(visitEntity.getId())
                .createDate(visitEntity.getCreateDate())
                .updateDate(visitEntity.getUpdateDate())
                .version(visitEntity.getVersion())
                .status(visitEntity.getStatus())
                .timeInDuty(visitEntity.getTimeInDuty())
                .price(visitEntity.getPrice())
                .doctor(doctorDto)
                .applicationUser(applicationUserDto)
                .duty(dutyDto)
                .build();
    }

    public static VisitEntity toVisitEntity (VisitDto visitDto){
        if (visitDto == null){
            return null;
        }
        DoctorEntity doctorEntity = DoctorMapper.toDoctorEntity(visitDto.getDoctor());
        ApplicationUser applicationUser = ApplicationUserMapper2.toApplicationUser(visitDto.getApplicationUser());
        DutyEntity dutyEntity = DutyMapper.toDutyEntity(visitDto.getDuty());
        
        VisitEntity visitEntity = new VisitEntity();
        visitEntity.setId(visitDto.getId());
        visitEntity.setCreateDate(visitDto.getCreateDate());
        visitEntity.setUpdateDate(visitDto.getUpdateDate());
        visitEntity.setVersion(visitDto.getVersion());
        visitEntity.setStatus(visitDto.getStatus());
        visitEntity.setTimeInDuty(visitDto.getTimeInDuty());
        visitEntity.setPrice(visitDto.getPrice());
        visitEntity.setDoctor(doctorEntity);
        visitEntity.setApplicationUser(applicationUser);
        visitEntity.setDuty(dutyEntity);
        return visitEntity;
    }

    public static List<VisitDto> toVisitDtos(List<VisitEntity> visitListByCriteria) {
        return visitListByCriteria.stream()
                .map(VisitMapper::toVisitDto)
                .collect(Collectors.toList());
    }
}
