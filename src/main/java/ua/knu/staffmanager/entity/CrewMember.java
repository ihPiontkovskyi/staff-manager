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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@Table(name = "crew_members")
public class CrewMember {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Integer id;

    @Column(name = "full_name", nullable = false)
    @NotEmpty
    private String fullName;

    @Column(name = "role", nullable = false)
    @NotEmpty
    private String role;

    @Override
    public String toString() {
        return role + ": " + fullName;
    }
}
