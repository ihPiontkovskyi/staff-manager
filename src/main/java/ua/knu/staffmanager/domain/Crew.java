package ua.knu.staffmanager.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Builder
public class Crew {

    private Integer id;

    @NotNull(message = "crew.should.have.captain")
    private Staff captain;

    @NotNull(message = "crew.should.have.second.pilot")
    private Staff secondPilot;

    @NotEmpty(message = "crew.should.have.crew.members")
    private List<Staff> crewMembers;
}
