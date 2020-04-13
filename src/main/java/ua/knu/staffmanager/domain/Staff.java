package ua.knu.staffmanager.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Builder
public class Staff {

    private Integer id;

    @NotEmpty(message = "staff.should.have.full.name")
    private String fullName;

    @NotEmpty(message = "staff.should.have.identifier")
    private String identifier;

    @NotEmpty(message = "staff.should.have.password")
    private String password;

    @NotNull(message = "staff.should.have.role")
    private Role role;

    @NotNull(message = "staff.should.have.location")
    private Airport location;
}
