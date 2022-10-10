package com.project.spring.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "create_date", length = 6, nullable = false)
    private LocalDateTime createDate;
    @Column(name = "update_date", length = 6, nullable = false)
    private LocalDateTime updateDate;
    @Version
    @Column(name = "version", length = 19, nullable = false)
    private Long version;

    @PrePersist
    public void createDate() {
        this.createDate = LocalDateTime.now();
        this.updateDate = createDate;
    }

    @PreUpdate
    public void updateDate() {
        this.updateDate = LocalDateTime.now();
    }
}
