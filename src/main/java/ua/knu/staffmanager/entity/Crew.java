package ua.knu.staffmanager.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "crews")
public class Crew {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "identifier", nullable = false, unique = true)
    private String identifier;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "captain_id", nullable = false)
    private CrewMember captain;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "second_pilot_id", nullable = false)
    private CrewMember secondPilot;

    @OneToMany(cascade = CascadeType.DETACH)
    @JoinColumn(name = "crew_members")
    private List<CrewMember> crewMembers;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "airport_id")
    private Airport location;

    @Override
    public String toString() {
        return identifier + ",Captain - " + captain +
                ",Second pilot - " + secondPilot;
    }
}
