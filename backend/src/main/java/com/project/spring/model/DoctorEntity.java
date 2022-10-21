package com.project.spring.model;

import com.project.spring.model.enums.MedicalSpecialization;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

import static com.project.spring.model.DoctorEntity.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "doctor")
public class DoctorEntity extends PersonEntity {

    @Column(name = "specialization", nullable = false)
    @Enumerated(EnumType.STRING)
    private MedicalSpecialization specialization;

    @Column(name = "hourly_rate", nullable = false)
    private BigDecimal hourlyRate;

    @Lob
    @Column(name = "discription")
    private String description;

    @Column(name = "img_url")
    private String imgUrl;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private MedicalClinicEntity clinic;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private Set<DutyEntity> duties;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private Set<VisitEntity> visits;

}
