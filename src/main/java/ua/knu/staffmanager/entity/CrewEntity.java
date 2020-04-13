package ua.knu.staffmanager.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter
@Builder
@Entity
@Table(name = "crew")
public class CrewEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "captain_id", nullable = false)
    private StaffEntity captain;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "second_pilot_id", nullable = false)
    private StaffEntity secondPilot;

    @OneToMany(cascade = CascadeType.DETACH)
    @JoinColumn(name = "crew_members", nullable = false)
    private List<StaffEntity> crewMembers;
}
