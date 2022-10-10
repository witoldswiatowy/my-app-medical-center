package com.project.spring.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "medical_clinic")
public class MedicalClinicEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL)
    private Set<DoctorEntity> doctors;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MedicalClinicEntity clinic = (MedicalClinicEntity) o;
        return getId() != null && Objects.equals(getId(), clinic.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
