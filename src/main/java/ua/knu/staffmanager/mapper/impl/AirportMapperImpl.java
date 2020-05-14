package ua.knu.staffmanager.mapper.impl;

import org.springframework.stereotype.Component;
import ua.knu.staffmanager.domain.Airport;
import ua.knu.staffmanager.entity.AirportEntity;
import ua.knu.staffmanager.mapper.AirportMapper;

import static java.util.Objects.isNull;

@Component
public class AirportMapperImpl implements AirportMapper {
    @Override
    public AirportEntity mapToEntity(Airport airport) {
        if (isNull(airport)) {
            throw new IllegalArgumentException("Airport is null!");
        }
        return AirportEntity.builder()
                .city(airport.getCity())
                .iata(airport.getIata())
                .id(airport.getId())
                .build();
    }

    @Override
    public Airport mapToDomain(AirportEntity airportEntity) {
        if (isNull(airportEntity)) {
            throw new IllegalArgumentException("Airport entity is null!");
        }
        return Airport.builder()
                .city(airportEntity.getCity())
                .iata(airportEntity.getIata())
                .id(airportEntity.getId())
                .build();
    }
}
