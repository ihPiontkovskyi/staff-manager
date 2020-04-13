package ua.knu.staffmanager.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Builder
@Entity
@Table(name = "airport")
public class AirportEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "iata", nullable = false, unique = true)
    private String iata;

    @Column(name = "city", nullable = false)
    private String city;
}
