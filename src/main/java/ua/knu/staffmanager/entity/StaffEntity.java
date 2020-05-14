package ua.knu.staffmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "staff")
public class StaffEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "identifier", nullable = false, unique = true)
    private String identifier;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private RoleEntity roleEntity;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "airport_id")
    private AirportEntity location;

}
