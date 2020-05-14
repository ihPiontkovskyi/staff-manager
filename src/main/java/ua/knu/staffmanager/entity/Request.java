package ua.knu.staffmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "requests")
public class Request {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "doctor_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private RequestStatus examinedByDoctor;

    @Column(name = "instructor_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private RequestStatus examinedByInstructor;

    @Column(name = "rejected_caues")
    private String rejectedCause;

    @OneToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Staff assignedDoctor;

    @OneToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    private Staff assignedInstructor;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "crew_id", nullable = false)
    private Crew crew;

    @OneToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;
}
