package ua.knu.staffmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
import javax.validation.constraints.NotNull;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "requests")
public class Request {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Integer id;

    @Column(name = "doctor_status", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private RequestStatus examinedByDoctor;

    @Column(name = "instructor_status", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private RequestStatus examinedByInstructor;

    @Column(name = "rejected_cause")
    private String rejectedCause;

    @OneToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    @NotNull
    private Staff assignedDoctor;

    @OneToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    @NotNull
    private Staff assignedInstructor;

    @OneToOne
    @JoinColumn(name = "crew_id", nullable = false)
    @NotNull
    private Crew crew;

    @OneToOne
    @JoinColumn(name = "flight_id", nullable = false)
    @NotNull
    private Flight flight;
}
