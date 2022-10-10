package com.project.spring.model;

import com.project.spring.model.enums.Sex;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Data // getter // setter // toString // equalsAndHashCode // requiredArgsConstructor
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Column(name = "create_date")
//    private LocalDateTime createDate;
//    @Column(name = "update_date")
//    private LocalDateTime updateDate;
//    @Version
//    @Column(name = "version")
//    private Long version;

    @Column(unique = true, nullable = false)
    private String username;
    private String password;

    private String firstName;
    private String lastName;
//    private String phoneNumber;
//    private String email;
//
//    @Enumerated(EnumType.STRING)
//    private Sex sex;
//
//    @Column(name = "birth_date")
//    private LocalDate birthDate;

    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

//    @OneToMany(mappedBy = "applicationUser", cascade = CascadeType.ALL)
//    private Set<VisitEntity> visits;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    // Linked security entities
    //
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<ApplicationUserRole> roles;

}
