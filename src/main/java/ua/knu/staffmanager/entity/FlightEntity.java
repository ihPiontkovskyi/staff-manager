package ua.knu.staffmanager.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Builder
@Entity
@Table(name = "flight")
public class FlightEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusEntity status;

    @Column(name = "extra_time", nullable = false)
    private Integer extraTime;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "crew_id", nullable = false)
    private CrewEntity crewEntity;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "destination_airport_id", nullable = false)
    private AirportEntity destination;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "arrival_airport_id", nullable = false)
    private AirportEntity arrival;
}
