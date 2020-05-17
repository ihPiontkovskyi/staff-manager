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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "crews")
public class Crew {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Integer id;

    @Column(name = "identifier", nullable = false, unique = true)
    @Pattern(regexp = "^[a-zA-Z]{3}[0-9]{6}$")
    private String identifier;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "captain_id", nullable = false)
    @NotNull
    private CrewMember captain;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "second_pilot_id", nullable = false)
    @NotNull
    private CrewMember secondPilot;

    @OneToMany(cascade = CascadeType.DETACH)
    @JoinColumn(name = "crew_members")
    private List<CrewMember> crewMembers;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "airport_id", nullable = false)
    @NotNull
    private Airport location;

    @Override
    public String toString() {
        return identifier + "[Captain : " + captain +"]";
    }
}
