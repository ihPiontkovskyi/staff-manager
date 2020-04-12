package ua.knu.staffmanager.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Builder
@Entity
@Table(name = "flight")
public class Flight {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "extra_time", nullable = false)
    private Integer extraTime;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "crew_id", nullable = false)
    private Crew crew;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "destination_airport_id", nullable = false)
    private Airport destination;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "arrival_airport_id", nullable = false)
    private Airport arrival;
}
