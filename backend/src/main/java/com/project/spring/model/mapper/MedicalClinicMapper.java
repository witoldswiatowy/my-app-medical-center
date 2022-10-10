package com.project.spring.model.mapper;

import com.project.spring.model.MedicalClinicEntity;
import com.project.spring.model.dto.MedicalClinicDto;

public class MedicalClinicMapper {

    private MedicalClinicMapper() {
    }

    public static MedicalClinicDto toMedicalClinicDto(MedicalClinicEntity medicalClinicEntity) {
        if (medicalClinicEntity == null) {
            return null;
        }
        return MedicalClinicDto.builder()
                .id(medicalClinicEntity.getId())
                .createDate(medicalClinicEntity.getCreateDate())
                .updateDate(medicalClinicEntity.getUpdateDate())
                .version(medicalClinicEntity.getVersion())
                .name(medicalClinicEntity.getName())
                .build();
    }

    public static MedicalClinicEntity toMedicalClinicEntity(MedicalClinicDto medicalClinicDto) {
        if (medicalClinicDto == null) {
            return null;
        }
        MedicalClinicEntity medicalClinicEntity = new MedicalClinicEntity();
        medicalClinicEntity.setId(medicalClinicDto.getId());
        medicalClinicEntity.setCreateDate(medicalClinicDto.getCreateDate());
        medicalClinicEntity.setUpdateDate(medicalClinicDto.getUpdateDate());
        medicalClinicEntity.setVersion(medicalClinicDto.getVersion());
        medicalClinicEntity.setName(medicalClinicDto.getName());
        return medicalClinicEntity;
    }
}