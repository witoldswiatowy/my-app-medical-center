package com.project.spring.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "duty")
public class DutyEntity extends BaseEntity {

    @Column(name = "duty_from", nullable = false)
    private LocalDateTime dutyFrom;

    @Column(name = "duty_to", nullable = false)
    private LocalDateTime dutyTo;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private DoctorEntity doctor;

    @OneToMany(mappedBy = "duty", cascade = CascadeType.ALL)
    private Set<VisitEntity> visits;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DutyEntity duty = (DutyEntity) o;
        return getId() != null && Objects.equals(getId(), duty.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
