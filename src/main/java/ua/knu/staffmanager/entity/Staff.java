package ua.knu.staffmanager.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Builder
@Entity
@Table(name = "staff")
public class Staff {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "fullName", nullable = false)
    private String fullName;

    @Column(name = "identifier", nullable = false, unique = true)
    private String identifier;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "airport_id", nullable = false)
    private Airport location;
}
