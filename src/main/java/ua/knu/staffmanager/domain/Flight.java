package ua.knu.staffmanager.domain;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Builder
public class Flight {

    private Integer id;

    @NotNull(message = "flight.should.have.status")
    private Status status;

    @PositiveOrZero(message = "flight.extra.time.shouldn't.be.less.0")
    private Integer extraTime;

    @NotNull(message = "flight.should.have.crew")
    private Crew crew;

    @NotNull(message = "flight.should.have.destination")
    private Airport destination;

    @NotNull(message = "flight.should.have.arrival")
    private Airport arrival;
}
