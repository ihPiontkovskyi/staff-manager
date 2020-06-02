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
import javax.validation.constraints.Pattern;

@Data
@Entity
@NoArgsConstructor
@Table(name = "flights")
public class Flight {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Integer id;

    @Column(name = "identifier", nullable = false, unique = true)
    @Pattern(regexp = "^[A-Z]{2}[0-9]{4}$")
    private String identifier;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "from_id")
    @NotNull
    private Airport from;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "to_id")
    @NotNull
    private Airport to;

    @Override
    public String toString() {
        return identifier + ": " + from + " - " + to;

    }
}
