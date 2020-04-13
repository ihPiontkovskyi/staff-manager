package ua.knu.staffmanager.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Builder
public class Airport {

    private Integer id;

    @NotEmpty(message = "IATA.code.is.empty")
    private String iata;

    @NotEmpty(message = "airport.city.is.empty")
    private String city;
}
