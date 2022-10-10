package com.project.spring.model.mapper;

import com.project.spring.model.DoctorEntity;
import com.project.spring.model.DutyEntity;
import com.project.spring.model.dto.DoctorDto;
import com.project.spring.model.dto.DutyDto;

public class DutyMapper {

    private DutyMapper() {
    }

    public static DutyDto toDutyDto(DutyEntity dutyEntity) {
        if (dutyEntity == null) {
            return null;
        }

        DoctorDto doctorDto = DoctorMapper.toDoctorDto(dutyEntity.getDoctor());

        return DutyDto.builder()
                .id(dutyEntity.getId())
                .createDate(dutyEntity.getCreateDate())
                .updateDate(dutyEntity.getUpdateDate())
                .version(dutyEntity.getVersion())
                .dutyFrom(dutyEntity.getDutyFrom())
                .dutyTo(dutyEntity.getDutyTo())
                .doctor(doctorDto)
                .build();
    }

    public static DutyEntity toDutyEntity(DutyDto dutyDto) {
        if (dutyDto == null) {
            return null;
        }
        DoctorEntity doctorEntity = DoctorMapper.toDoctorEntity(dutyDto.getDoctor());

        DutyEntity dutyEntity = new DutyEntity();
        dutyEntity.setId(dutyDto.getId());
        dutyEntity.setCreateDate(dutyDto.getCreateDate());
        dutyEntity.setUpdateDate(dutyDto.getUpdateDate());
        dutyEntity.setVersion(dutyDto.getVersion());
        dutyEntity.setDutyFrom(dutyDto.getDutyFrom());
        dutyEntity.setDutyTo(dutyDto.getDutyTo());
        dutyEntity.setDoctor(doctorEntity);
        return dutyEntity;
    }

}
