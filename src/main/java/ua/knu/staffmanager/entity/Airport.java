package ua.knu.staffmanager.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Entity
@NoArgsConstructor
@Table(name = "airports")
public class Airport {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Integer id;

    @Column(name = "iata", nullable = false, unique = true)
    @Pattern(regexp = "^[A-Z]{3}$")
    private String iata;

    @Column(name = "city", nullable = false)
    @NotEmpty
    private String city;

    @Override
    public String toString() {
        return city + "(" + iata + ")";
    }
}
