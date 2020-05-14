package ua.knu.staffmanager.service.mapper.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.knu.staffmanager.domain.Flight;
import ua.knu.staffmanager.domain.Status;
import ua.knu.staffmanager.entity.FlightEntity;
import ua.knu.staffmanager.entity.StatusEntity;
import ua.knu.staffmanager.service.mapper.AirportMapper;
import ua.knu.staffmanager.service.mapper.CrewMapper;
import ua.knu.staffmanager.service.mapper.FlightMapper;

import static java.util.Objects.isNull;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FlightMapperImpl implements FlightMapper {

    private final AirportMapper airportMapper;
    private final CrewMapper crewMapper;

    @Override
    public FlightEntity mapToEntity(Flight flight) {
        if (isNull(flight)) {
            throw new IllegalArgumentException("Flight is null!");
        }
        return FlightEntity.builder()
                .arrival(airportMapper.mapToEntity(flight.getArrival()))
                .crewEntity(crewMapper.mapToEntity(flight.getCrew()))
                .destination(airportMapper.mapToEntity(flight.getDestination()))
                .extraTime(flight.getExtraTime())
                .id(flight.getId())
                .status(StatusEntity.valueOf(flight.getStatus().name()))
                .build();
    }

    @Override
    public Flight mapToDomain(FlightEntity flightEntity) {
        if (isNull(flightEntity)) {
            throw new IllegalArgumentException("Flight entity is null!");
        }
        return Flight.builder()
                .arrival(airportMapper.mapToDomain(flightEntity.getArrival()))
                .crew(crewMapper.mapToDomain(flightEntity.getCrewEntity()))
                .destination(airportMapper.mapToDomain(flightEntity.getDestination()))
                .extraTime(flightEntity.getExtraTime())
                .id(flightEntity.getId())
                .status(Status.valueOf(flightEntity.getStatus().name()))
                .build();
    }
}
