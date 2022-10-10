package com.project.spring.model;

import com.project.spring.model.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "visit")
public class VisitEntity extends BaseEntity {


    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "time_in_duty", nullable = false)
    private LocalDateTime timeInDuty;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private DoctorEntity doctor;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private ApplicationUser applicationUser;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private DutyEntity duty;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        VisitEntity that = (VisitEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
