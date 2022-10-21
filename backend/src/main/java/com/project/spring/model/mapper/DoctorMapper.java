package com.project.spring.model.mapper;

import com.project.spring.model.DoctorEntity;
import com.project.spring.model.MedicalClinicEntity;
import com.project.spring.model.dto.AddDoctorRequest;
import com.project.spring.model.dto.DoctorDto;
import com.project.spring.model.dto.MedicalClinicDto;

import java.util.List;
import java.util.stream.Collectors;

public class DoctorMapper {

    private DoctorMapper() {
    }

    public static DoctorDto toDoctorDto (DoctorEntity doctorEntity){
        if (doctorEntity == null){
            return null;
        }

        MedicalClinicDto clinicDto = MedicalClinicMapper.toMedicalClinicDto(doctorEntity.getClinic());

        return DoctorDto.builder()
                .id(doctorEntity.getId())
                .createDate(doctorEntity.getCreateDate())
                .updateDate(doctorEntity.getUpdateDate())
                .version(doctorEntity.getVersion())
                .firstName(doctorEntity.getFirstName())
                .lastName(doctorEntity.getLastName())
                .phoneNumber(doctorEntity.getPhoneNumber())
                .email(doctorEntity.getEmail())
                .specialization(doctorEntity.getSpecialization())
                .hourlyRate(doctorEntity.getHourlyRate())
                .clinic(clinicDto)
                .build();
    }

    public static DoctorEntity toDoctorEntity (DoctorDto doctorDto){
        if (doctorDto == null){
            return null;
        }
        MedicalClinicEntity clinicEntity = MedicalClinicMapper.toMedicalClinicEntity(doctorDto.getClinic());

        DoctorEntity doctorEntity = new DoctorEntity();
        doctorEntity.setId(doctorDto.getId());
        doctorEntity.setCreateDate(doctorDto.getCreateDate());
        doctorEntity.setUpdateDate(doctorDto.getUpdateDate());
        doctorEntity.setVersion(doctorDto.getVersion());
        doctorEntity.setFirstName(doctorDto.getFirstName());
        doctorEntity.setLastName(doctorDto.getLastName());
        doctorEntity.setPhoneNumber(doctorDto.getPhoneNumber());
        doctorEntity.setEmail(doctorDto.getEmail());
        doctorEntity.setSpecialization(doctorDto.getSpecialization());
        doctorEntity.setHourlyRate(doctorDto.getHourlyRate());
        doctorEntity.setClinic(clinicEntity);
        return doctorEntity;
    }

    public static DoctorEntity requestToDoctorEntity (AddDoctorRequest request){
        if (request == null){
            return null;
        }
        DoctorEntity doctorEntity = new DoctorEntity();
        doctorEntity.setFirstName(request.getFirstName());
        doctorEntity.setLastName(request.getLastName());
        doctorEntity.setPhoneNumber(request.getPhoneNumber());
        doctorEntity.setEmail(request.getEmail());
        doctorEntity.setSpecialization(request.getSpecialization());
        doctorEntity.setHourlyRate(request.getHourlyRate());
        return doctorEntity;
    }

    public static List<DoctorDto> toDoctorDtos(List<DoctorEntity> doctorEntities) {
        return doctorEntities.stream()
                .map(DoctorMapper::toDoctorDto)
                .collect(Collectors.toList());
    }

}
